-- openresty with lua redis module

local redis_host = "redis"
local redis_port = 6379

local redis_connection_timeout = 90

local redis_key = "ip_blacklist"

local cache_ttl = 60

local ip = ngx.var.remote_addr
local ip_blacklist = ngx.shared.ip_blacklist
local last_update_time = ip_blacklist:get("last_update_time");

if last_update_time == nil or last_update_time < (ngx.now() - cache_ttl) then
    local redis = require "resty.redis";
    local red = redis:new();
    red:set_timeout(redis_connection_timeout);
    local ok, err = red:connect(redis_host, redis_port)
    if not ok then 
        ngx.log(ngx.DEBUG, "Redis connection error while retrieving ip_blacklist: " ..err)
    else
        local new_ip_blacklist, err = red:smember(redis_key)
        if err then
            ngx.log(ngx.DEBUG, "Redis read error while retrieving ip_blacklist: " ..err)
        else
            ip_blacklist:flush_all()
            for index, banned_ip in ipairs(new_ip_blacklist) do
                ip_blacklist:set(banned_ip, true);
            end
            ip_blacklist:set("last_update_time", ngx.now())
        end
    end
end

if ip_blacklist:get(ip) then
    ngx.log(ngx.DEBUG, "Banned IP detected and refused access: ".. ip);
    return ngx.exit(ngx.HTTP_FORBIDDEN);
end
