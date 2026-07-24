class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] paired = new int[n][3];
        for(int i=0; i<n; i++){
            paired[i][0] = tasks[i][0];
            paired[i][1] = tasks[i][1];
            paired[i][2] = i;
        }
        Arrays.sort(paired, (a, b) -> a[0]-b[0]); // sort by arrival time

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1]==b[1]) return a[2] - b[2]; // smallest index
            return a[1]-b[1]; // burst time
            });

        int[] order = new int[n];
        int itr = 0;
        int currTime = paired[0][0];
        int i=0;
        while(i<n){
            int temp = i;
            while(i<n && currTime>=paired[i][0]){
                pq.add(new int[]{paired[i][0], paired[i][1], paired[i][2]}); // enqueue all the jobs in mean-time while current is processing with BT <= currTime
                i++; // <-------------
            }
            if(pq.isEmpty()) currTime = paired[i][0];
            else{
                int[] rem = pq.poll();
                currTime += rem[1];
                order[itr++] = rem[2];
            }
            // i++; no need for this as inner while loop already making i to point the next task
        }
        while(!pq.isEmpty()){
            int[] rem = pq.poll(); // get the job with min burst time
            currTime += rem[1];
            order[itr++] = rem[2];
        }
        return order;
    }
}