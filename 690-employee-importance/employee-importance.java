class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> inputMap = new HashMap<>();
		// Construct HashMap as getting the employee from id is difficult in a list
		for(Employee e : employees) {
			inputMap.put(e.id, e);
		}
		return helper(inputMap, id);
	}

	private static int helper(Map<Integer, Employee> inputMap, int id) {
		//Get the importance of the employee
		int imp = inputMap.get(id).importance;
		
		//Add importance of subordinates to employee importance
		for(int subId : inputMap.get(id).subordinates) {
			imp += helper(inputMap, subId);
		}
		
		return imp;
	}
}