package main

import "fmt"

func dfs(array []int, current int) {

	if current == 0 {
		pretty_print(array) // solution found
		return
	}

	for i := 0; i < len(array) - 1 - current; i++ {
		if array[i] == 0 && array[i + current + 1] == 0 {
			array[i] = current
			array[i + current + 1] = current
			dfs(array, current - 1)
			array[i] = 0
			array[i + current + 1] = 0
		}
	}
}

// prints the array of ints as characters (1 -> A, 2 -> B, ...)
func pretty_print(array []int) {

	for _, c := range (array) {
		if c == 0 {
			fmt.Print("-")
		} else {
			fmt.Print(string(64 + c))
		}
	}
	fmt.Println()
}

func main() {

	array := make([]int, 53)
	dfs(array, 26)
}
