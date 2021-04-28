package algorithm.greedy;


import algorithm.util.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 会议室排期问题，贪心
 */
public class Code01_MeetingRoomSchedule {

    public static void main(String[] args) {
//        Meeting[] arr = generateRandomMeetings(10);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
//
//        P.divider();
//
//        List<Meeting> schedule1 = greedySolve(arr);
//
//        P.divider();
//
//        System.out.println("count==" + schedule1.size() + ";schedule = " + schedule1);
//
//        List<Integer> schedule2Indexes = violentSolve(arr, new ArrayList<>(), 0);
//        List<Meeting> schedule2 = new ArrayList<>(schedule2Indexes.size());
//        for (Integer i : schedule2Indexes) {
//            schedule2.add(arr[i]);
//        }
//
//        System.out.println("count==" + schedule2.size() + ";schedule = " + schedule2);

        check(500000, 10);
    }

    public static void check(int times, int meetingNum) {
        Meeting[] arr = null;
        try {
            while (times-- > 0) {
                arr = generateRandomMeetings(meetingNum);
                List<Meeting> schedule1 = greedySolve(arr);
                List<Integer> schedule2 = violentSolve(arr, new ArrayList<>(), 0);
//                System.out.println("count==" + schedule1.size() + ";schedule = " + schedule1);
//                System.out.println("count==" + schedule2.size() + ";schedule2 = " + schedule2);

                if (schedule1.size() != schedule2.size()) {
                    throw new RuntimeException("fucking fucked!!!!!!");
                }
            }

            System.out.println("成功!!!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    public static Meeting[] generateRandomMeetings(int num) {
        Meeting[] arr = new Meeting[num];
        for (int i = 0; i < num; i++) {
            int end = Checker.generateRandomPositiveNumNoMoreThan(24);
            int begin = Checker.generateRandomPositiveNumNoMoreThan(end - 1);
            arr[i] = new Meeting(begin, end);
        }

        return arr;
    }

    public static class Meeting {
        public int begin;
        public int end;

        public Meeting(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public String toString() {
            return "{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}';
        }
    }

    public static class EndEarlyComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;
        }
    }

    /**
     * 贪心解
     */
    public static List<Meeting> greedySolve(Meeting[] arr) {
        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }

        // 按会议结束时间早来贪
        // 结束的会议更有机会让别的会议排进来
        List<Meeting> meetings = Arrays.asList(arr);
        meetings.sort(new EndEarlyComparator());

//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }

        // 会议室可以使用的时间点
        int available = 0;
        List<Meeting> schedule = new ArrayList<>();
        for (Meeting m : meetings) {
            if (m.begin >= available) {
                available = m.end;
                schedule.add(m);
            }
        }

        return schedule;
    }

    /**
     * 暴力解
     *
     * @param meetings  会议列表
     * @param arranged  已经安排的会议的下标
     * @param available 会议室可以使用的时间点
     * @return
     */
    public static List<Integer> violentSolve(Meeting[] meetings, List<Integer> arranged, int available) {
        if (meetings == null || meetings.length == 0) {
            return arranged;
        }

        List<Integer> bestPlan = arranged;
        for (int i = 0; i < meetings.length; i++) {
            if (bestPlan.contains(i)) {
                continue;
            }

            Meeting m = meetings[i];
            if (m.begin >= available) {
                List<Integer> arrangedCopy = copy(arranged);
                arrangedCopy.add(i);
                List<Integer> retPlan = violentSolve(meetings, arrangedCopy, m.end);
                if (retPlan.size() > bestPlan.size()) {
                    bestPlan = retPlan;
                }
            }
        }

        return bestPlan;
    }

    private static List<Integer> copy(List<Integer> list) {
        List<Integer> list2 = new ArrayList<>(list.size());
        for (Integer i : list) {
            list2.add(i);
        }

        return list2;
    }
}
