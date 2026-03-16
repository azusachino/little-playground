// package cn.az.webflux.aeron;

// import org.agrona.DirectBuffer;
// import org.agrona.ExpandableDirectByteBuffer;

// import io.aeron.logbuffer.FragmentHandler;
// import io.aeron.logbuffer.Header;

// public class ClusterFragementHandler implements FragmentHandler{

//     private final RSM stateMachine;
//     private final AddCommandDecoder addCommand;
//     private final MultiplyCommandDecoder multiplyCommand;
//     private final SetCommandDecoder setCommand;
//     private final CurrentValueEventEncoder currentValue;
//     private final SnapshotDecoder snapshotDecoder;
//     private final MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
//     private final MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
//     private final Logger logger = LoggerFactory.getLogger(RsmAdapter.class);
//     private final ExpandableDirectByteBuffer returnBuffer;

//     @Override
//     public void onFragment(DirectBuffer buffer, int offset, int length, Header header) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'onFragment'");
//     }
    
// }
