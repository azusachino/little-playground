package cn.az.webflux.aeron;

import org.agrona.DirectBuffer;

import io.aeron.ExclusivePublication;
import io.aeron.Image;
import io.aeron.cluster.codecs.CloseReason;
import io.aeron.cluster.service.ClientSession;
import io.aeron.cluster.service.Cluster;
import io.aeron.cluster.service.Cluster.Role;
import io.aeron.cluster.service.ClusteredService;
import io.aeron.logbuffer.Header;

public class ClusterCallback implements ClusteredService {

    @Override
    public void onStart(Cluster cluster, Image snapshotImage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onStart'");
    }

    @Override
    public void onSessionOpen(ClientSession session, long timestamp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onSessionOpen'");
    }

    @Override
    public void onSessionClose(ClientSession session, long timestamp, CloseReason closeReason) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onSessionClose'");
    }

    @Override
    public void onSessionMessage(ClientSession session, long timestamp, DirectBuffer buffer, int offset, int length,
            Header header) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onSessionMessage'");
    }

    @Override
    public void onTimerEvent(long correlationId, long timestamp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onTimerEvent'");
    }

    @Override
    public void onTakeSnapshot(ExclusivePublication snapshotPublication) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onTakeSnapshot'");
    }

    @Override
    public void onRoleChange(Role newRole) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onRoleChange'");
    }

    @Override
    public void onTerminate(Cluster cluster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onTerminate'");
    }
    
}
