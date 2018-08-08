# Product of ints

You have an array of integers, and for each index you want to find the product of every integer except the integer at that index.
Write a function `get_products_of_all_ints_except_at_index()` that takes an array of integers and returns an array of the products.

For example, given:
    `[1, 7, 3, 4]`

your function would return:
    `[84, 12, 28, 21]`

by calculating:
    `[7*3*4, 1*3*4, 1*7*4, 1*7*3]`

Do not use division in your solution.

A brute force approach would use two loops to multiply the integer at every index by the integer at every `nested_index`, unless `index == nested_index`. This would give us a runtime of `O(n2)`. 

Can you implement a solution with `O(n)` time and `O(n)` space complexity?

