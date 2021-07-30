package searchEngine;

import java.util.List;

public class PageRanking {

	public float pageRank(List<List<Integer>> result) {

		if (result.isEmpty()) {
			return 0.0f;
		}

		if (result.size() == 1) {
			return result.get(0).size();
		}

		float pageRank = 0.0f;
		Boolean flag = false;
		List<Integer> firstQuery = result.get(0);

		for (int j = 0; j < result.size(); j++) {
			if (result.get(j).size() > 0)
				pageRank++;
		}

		for (int i = 0; i < firstQuery.size(); i++) {
			flag = true;
			for (int j = 1; j < result.size(); j++) {
				int numberSearch = firstQuery.get(i) + j;
				if (!result.get(j).contains(numberSearch)) {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				pageRank += 100;
			}
		}
		return pageRank;
	}
}
