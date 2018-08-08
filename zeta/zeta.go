package main

import (
	"fmt" 
	"math"
	"os"
	"strconv"
)

const R_MAX = 10000000

/** 
 * Calculates the value of the zeta function for exponent n.
 */
func zeta(n int) float64 {
	sum := 0.0
	for r := 1; r < R_MAX; r++ {
		sum += (1/math.Pow(float64(r), float64(n)))
	}
	return sum
}

func main() {
	n_max,_ := strconv.Atoi(os.Args[1])
	fmt.Println("Max N: ", os.Args[1])
	for n := 2; n < n_max; n++ {
		fmt.Println("N:",n,", zeta(N):",zeta(n))
	}
}
