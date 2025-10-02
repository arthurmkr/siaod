package ru.mab.siaod.leetcode;

/**
 * 933. Number of Recent Calls
 */
public class NumberOfRecentCalls {
    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        for(int i = 1; i< 3010; i++) {
            System.out.println(i + " : " + recentCounter.ping(i));     // requests = [1], range is [-2999,1], return 1
        }
//        System.out.println(recentCounter.ping(1) == 1);     // requests = [1], range is [-2999,1], return 1
//        System.out.println(recentCounter.ping(100) == 2);   // requests = [1, 100], range is [-2900,100], return 2
//        System.out.println(recentCounter.ping(3001) == 3);  // requests = [1, 100, 3001], range is [1,3001], return 3
//        System.out.println(recentCounter.ping(3002) == 3);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
    }
}

//class RecentCounter {
//    Queue<Integer> queue = new ArrayDeque<>();
//
//    public RecentCounter() {
//
//    }
//
//    public int ping(int t) {
//        while (!queue.isEmpty() && t - queue.peek() > 3000) {
//            queue.poll();
//        }
//
//        queue.add(t);
//
//        return queue.size();
//    }
//}

class RecentCounter {
    int[] queue = new int[3001];
    int start = 0;
    int end = 0;
    int cnt = 0;

    public RecentCounter() {

    }

    public int ping(int t) {
        while (cnt > 0 && t - queue[start] > 3000) {
            start = (start + 1) % queue.length;
            cnt--;
        }

        queue[end] = t;
        end = (end + 1) % queue.length;
        cnt++;
        return cnt;
    }
}
