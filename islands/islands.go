package main

import "fmt"

type Location struct {
	x int
	y int
}

func is_unvisited_island(i int, j int, grid [][]byte) bool {

	if i < 0 || j < 0 || i >= len(grid) || j >= len(grid[0]) {
		return false
	}
	return grid[i][j] == 1
}

// This will essentially do a depth first search of all connected points
func visit(i int, j int, grid [][]byte) {

	grid[i][j] = 2 // mark this node as visited

	unvisited := make([]Location, 0)

	// TODO maybe write a loop to test all neighbours
	if is_unvisited_island(i-1, j, grid) {
		unvisited = append(unvisited, Location{x: i - 1, y: j})
	}
	if is_unvisited_island(i+1, j, grid) {
		unvisited = append(unvisited, Location{x: i + 1, y: j})
	}
	if is_unvisited_island(i, j-1, grid) {
		unvisited = append(unvisited, Location{x: i, y: j - 1})
	}
	if is_unvisited_island(i, j+1, grid) {
		unvisited = append(unvisited, Location{x: i, y: j + 1})
	}

	for _, island := range unvisited {
		visit(island.x, island.y, grid)
	}
}

func count_islands(grid [][]byte) int {

	islands := 0

	for i := range grid {
		for j := range grid[i] {
			if is_unvisited_island(i, j, grid) {
				islands++
				visit(i, j, grid)
			}
		}
	}

	return islands
}

func main() {

	in1 := [][]byte{
		{1, 1, 1, 1, 0},
		{1, 1, 0, 1, 0},
		{1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0}}

	in2 := [][]byte{
		{1, 1, 0, 0, 0},
		{1, 1, 0, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 0, 1, 1}}

	fmt.Print("Input1: ")
	fmt.Println(in1)
	fmt.Print("Result: ")
	fmt.Println(count_islands(in1))

	fmt.Print("Input2: ")
	fmt.Println(in2)
	fmt.Print("Result: ")
	fmt.Println(count_islands(in2))
}
