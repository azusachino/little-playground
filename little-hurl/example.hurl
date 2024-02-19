GET https://www.sample.net
x-app: MY_APP
HTTP 302
[Asserts]
header "Location" exists
header "Location" contains "login"
