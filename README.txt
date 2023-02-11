There are no special instructions to run. Just go to the MainActivity.kt and run the project. 

1) I implemented the $ symbol by doing "$" + totalCost but I easily could have tried "$${totalCost}" as well.
2) I used GeeksForGeek for the Jetpack composable information. For the radio buttons I used a list of option, then added a RadioButton composable to each option. 
	For the dropdown, I used an OutlinedTextField for the initial text field and its value. Then used a DropdownMenu composable for the contents of the dropdown portion.
	I looped through the pizzas and set the DropdownMenuItem to the pizza text. 