package main

import "fmt"

/*
 * Smart algorithm with O(n) time and space complexity 
 */
func get_products_of_all_ints_except_at_index(a []int) []int{

	/* if the array has 0 or zero elements, return an empty array */ 
	if (len(a) < 2) {
		return make([]int, 0)
	}

	p := make([]int, len(a))
	
	// for input array [a1, a2, a3, a4, ... ], create an array of products:
        // [1, a1, a1*a2, a1*a2*a3, ...]
	product := 1
	for i:=0; i < len(a); i++ {
		p[i] = product
		product *= a[i]
	}

	// now, go backwards and, maintain the product of last elements (a(n-1), a(n-2) * a(n-3), ...) in a 
	// temp variable, multiply it with the current element in p, and store the result
	product = 1
	for j:=len(a)-1; j >= 0; j-- {
		p[j] *= product
		product = product * a[j]
	} 

	return p 
}

/**
 * Naive algorithm with O(n^2) time complexity
 */ 
func naive(a []int) []int {

	/* if the array has 0 or zero elements, return an empty array */ 
	if (len(a) < 2) {
		return make([]int, 0)
	}

	p := make([]int, len(a))
	for i := 0; i < len(a); i++ {
	
		product := 1; 
		for j := 0; j < len(a); j++ {
			if i != j {
				product = product * a[j]
			}
 		}
		p[i] = product;
	}

	return p
}


func main() {
	
	a := []int{1, 7, 3, 4}
	
	fmt.Print("Input: ")
	fmt.Println(a)
	
	fmt.Print("Output (naive):")
	fmt.Println(naive(a))

	fmt.Print("Output (fast):")
	fmt.Println(get_products_of_all_ints_except_at_index(a))
}
