/**
 * PROBLEM STATEMENT: 2069. Walking Robot Simulation II
 * --------------------------------------------------------------------------------
 * A width x height grid is on an XY-plane. Bottom-left is (0,0), top-right is (width-1, height-1).
 * The robot starts at (0,0) facing "East".
 * * Commands:
 * - step(num): Move 'num' steps. If hitting a boundary, turn 90 deg CCW and continue.
 * - getPos(): Return current [x, y].
 * - getDir(): Return current direction string ("North", "East", "South", "West").
 * * Special Case: If the robot completes one or more full loops and ends back at (0,0),
 * its direction should be "South" (because it had to turn from West to South to stay in bounds).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: PERIMETER PRECOMPUTATION
 * --------------------------------------------------------------------------------
 * 1. Observation: The robot only ever moves along the perimeter of the grid.
 * 2. Precomputation: 
 * - Calculate all possible positions along the boundary once in the constructor.
 * - Total boundary cells = 2 * (width + height) - 4.
 * - Store each cell as an array: [x, y, direction_at_that_cell].
 * 3. Direction Handling:
 * - Usually, the direction at a cell is fixed based on which edge it belongs to.
 * - Exception: At corners, the robot only turns AFTER it tries to move out of bounds.
 * - The (0,0) starting position is "East" initially, but "South" if the robot 
 * has moved at least one full perimeter length.
 * 4. Step Logic:
 * - Use modulo arithmetic: new_index = (current_index + num) % total_perimeter_cells.
 * - This makes the `step` operation O(1) after the initial O(Width + Height) setup.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity:
 * - Constructor: O(Width + Height) to traverse the perimeter.
 * - step(num): O(1) due to modulo arithmetic.
 * - getPos(): O(1).
 * - getDir(): O(1).
 * * Space Complexity: O(Width + Height) 
 * - To store the list of all coordinates on the perimeter.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

import java.util.ArrayList;
import java.util.List;

class Robot {
     int idx = 0;
    boolean moved = false;
    List<int[]> pos = new ArrayList<>(); 

    public Robot(int width, int height) {
        for (int x = 0; x < width; x++) {
            pos.add(new int[]{x, 0, 0});
        }

        for (int y = 1; y < height; y++) {
            pos.add(new int[]{width - 1, y, 1});
        }
        for (int x = width - 2; x >= 0; x--) {
            pos.add(new int[]{x, height - 1, 2});
        }
        for (int y = height - 2; y > 0; y--) {
            pos.add(new int[]{0, y, 3});
        }
        pos.get(0)[2] = 3;
    }

    public void step(int num) {
        moved = true;
        idx = (idx + num) % pos.size();
    }

    public int[] getPos() {
        return new int[]{pos.get(idx)[0], pos.get(idx)[1]};
    }

    public String getDir() {
        if (!moved) 
        return "East";

        int d = pos.get(idx)[2];

        if (d == 0) 
        return "East";
        
        else if (d == 1) return "North";
        else if (d == 2) return "West";
        return "South";
    }
}

