package GameEntities.Graphs;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestTreeGraph {

	@Test
	public void test() {
		//Checks structure
		Graph tree = new TreeGraph();
		assertNotEquals(null, tree);
	}

}
