
# Game of Life – Modular Java Implementation

Conway's **Game of Life** is a cellular automaton devised by mathematician John Conway. This project implements the Game of Life using **Java 17**, following clean code practices, modularization, exception handling, logging, and unit testing.

---

## Project Structure

```
game-of-life/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/gameoflife/
│   │   │       ├── GameOfLife.java              # Main runner
│   │   │       ├── model/
│   │   │       │   └── Grid.java                # Grid representation
│   │   │       ├── rules/
│   │   │       │   └── LifeRules.java           # Game rules
│   │   │       ├── pattern/
│   │   │       │   └── PatternInitializer.java  # Initial pattern seeding
│   │   │       └── exception/
│   │   │           └── InvalidPatternException.java
│   │   └── resources/
│   │       └── logback.xml                      # Logging config
│   └── test/
│       └── java/
│           └── com/example/gameoflife/
│               └── GameOfLifeTest.java          # Unit tests
```

---

## Setup Instructions

### Prerequisites

- Java 17+
- Maven 3.6+
- Git (optional)

### Clone the Repository

```bash
git clone https://github.com/your-username/game-of-life.git
cd game-of-life
```

### Build the Project

```bash
mvn clean install
```

### Run the Simulation

```bash
mvn exec:java -Dexec.mainClass="com.example.gameoflife.GameOfLife"
```

---

## How It Works

1. **Grid Initialization**:
   - A square grid of size `N x N` is initialized (default is `25x25`).
   - A pattern (e.g., Glider) is placed at the center.

2. **Game Rules** (per Conway's Life):
   - Any live cell with fewer than two live neighbours dies (underpopulation).
   - Any live cell with two or three live neighbours lives on to the next generation.
   - Any live cell with more than three live neighbours dies (overpopulation).
   - Any dead cell with exactly three live neighbours becomes a live cell (reproduction).

3. **Simulation Loop**:
   - Each generation is computed based on the previous grid.
   - Simulation runs for a defined number of generations (default: 10).
   - Grid prints to the console using symbols:
     - `⬛` – Live cell
     - `⬜` – Dead cell

4. **Patterns**:
   - Pattern support is modular; current implementation includes:
     - **Glider**
     - (More can be added easily via `PatternInitializer`)
5. **Time and Space Complexity**
   - Time Complexity	
     - O(N²) per generation, where N is the grid size (NxN). Every cell must be visited, 
      and neighbor checks are constant time.
   -Space Complexity	
     - O(N²), for storing the next generation grid (in addition to the current grid).
6. **Core Logic**
   - next[x][y] = (current[x][y] && (liveNeighbors == 2 || liveNeighbors == 3)) ||
   (!current[x][y] && liveNeighbors == 3);
   - This logic determines whether a cell lives or dies in the next generation, 
     depending on its current state and number of live neighbors.
---

## Customization

You can modify `GameOfLife.java` to:

- Change grid size (`SIZE`)
- Set number of generations
- Add or change patterns in `PatternInitializer`

---

## Testing

Run all unit tests with:

```bash
mvn test
```

Included Tests:
- Pattern behavior (e.g., blinker)
- Rule application validation
- Grid state transitions

---

## Exception Handling

- Custom `InvalidPatternException` is thrown for illegal pattern placement.
- All critical errors are logged using SLF4J + Logback.
- Defensive checks (e.g., grid size mismatch) ensure runtime safety.

---

## Logging

Logging output is controlled via `src/main/resources/logback.xml`:
- Logs are printed to console
- You can configure file logging or change log levels

Example log:

```
20:31:12.456 [main] INFO  com.example.gameoflife.GameOfLife - Generation: 3
```

---

##  Possible Improvements

- Space Optimization 
  - Instead of allocating a new next[][] grid, we can reuse a single grid and update
    cells in-place with bit manipulation or state markers (e.g., 0 → 1 for dead-to-alive).
- Edge Optimization
  - Implement a bounded grid or sparse grid using Set<Point> or Map<Point, Boolean> 
    to simulate infinite or sparse grids more efficiently.
- Parallel Processing
  - Since each cell update is independent, the next generation calculation can be 
    parallelized using Java threads, ExecutorService, or streams.

- Add new patterns (e.g., Pulsar, LWSS)
- Improve CLI support (pattern choice, dynamic size)
- Enhance performance or add graphical rendering

---

## License 
Author: **Subodh Kumar**
