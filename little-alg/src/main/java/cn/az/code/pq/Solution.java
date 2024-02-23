package cn.az.code.pq;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Priority Queue Problems
 */
class Solution {

    /**
     * MeetingRoomIII
     * 
     * @q_url https://leetcode.com/problems/meeting-rooms-iii
     * @link https://leetcode.com/problems/meeting-rooms-iii/solutions/2527280/c-with-explanation-magic-of-priority-queues
     */
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int[] count = new int[n];
        // {room_number, current_meeting_ending_time}
        PriorityQueue<int[]> engaged = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        // unused meeting room (number)
        PriorityQueue<Integer> unused = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            unused.offer(i);
        }
        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];
            // the occupation of oldest meeting room is over
            while (!engaged.isEmpty() && engaged.peek()[1] <= start) {
                int room = engaged.poll()[0];
                unused.offer(room);
            }

            if (unused.size() > 0) {
                int room = unused.poll();
                count[room]++;
                engaged.offer(new int[] { room, end });
            } else {
                int[] cur = engaged.poll();
                int room = cur[0], curEnd = cur[1];
                count[room]++;
                int newEnd = curEnd + end - start;
                engaged.offer(new int[] { room, newEnd });
            }
        }

        int maxRoomId = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[maxRoomId])
                maxRoomId = i;
        }
        return maxRoomId;
    }
}
