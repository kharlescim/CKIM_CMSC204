/*
 * Class: CMSC204 
 * Instructor: Kujit
 * Description: Password checker that checks whether passwords are valid/weak based on several requirements
 * Due: 02/16/2023
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Charles Kim
*/
public class Notation {
	/** after being passed two variables and an Operator to be used for calculation, this method performs the proper calculation
	 * between the two variables, depending on what the Operator is
	 */
	private static double operation(String op, String num1, String num2) {
		//parse both string variables to doubles
		double double1 = Double.parseDouble(num1);
		double double2 = Double.parseDouble(num2);
		
		//HELPER METHODS
		//depending on what the Operator is, perform a different operation and return the result
		switch(op) {
		case "+":
			return double1 + double2;
		case "-":
			return double1 - double2;
		case "*":
			return double1 * double2;
		case "/":
			return double1 / double2;
		}
		return 0;
	}
	
	//check whether string is Operator
	private static boolean isOperator(String op) {
		if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"))
			return true;
		return false;
	}
	
	//MAIN METHODS
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		
		MyStack<String> stack = new MyStack<String>(postfix.length());
		char[] copy = postfix.toCharArray();
		String num1, num2;
		
		try {
			for (char index : copy) {
				//skip over if it's whitespace
				if(index == ' ')
					continue;
				else if(Character.isDigit(index)) //if char is digit, push it to stack
					stack.push(Character.toString(index));
				
				//if char is op, pop latest two numbers and push again, but with parenthesis and the op in the middle
				else if(isOperator(Character.toString(index))) { 
					num2 = stack.pop();
					num1 = stack.pop();
					stack.push("(" + num1 + index + num2 + ")");
				}
			}
			//check whether result has more than one string
			if(stack.size() > 1)
				throw new InvalidNotationFormatException();
			//catching various exceptions 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stack.toString();
	}
	
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		MyQueue<String> queue = new MyQueue<String>(infix.length());
		MyStack<String> stack = new MyStack<String>(infix.length());
		char[] copy = infix.toCharArray();
		
		try {
			for (char index : copy) {
				//skip over if it's whitespace
				if(index == ' ')
					continue;
				
				//if it's digit, copy to queue
				else if(Character.isDigit(index))
					queue.enqueue(Character.toString(index));
				
				//if it's left parenthesis, push to stack
				else if(index == '(')
					stack.push(Character.toString(index));
				
				//if given + or -, check whether top of stack is any of the Operators, as they are all of equal or higher 
				//precedence of + and -. pop those out, then push index to queue	
				else if(index == '+' || index == '-') {
					if(!stack.isEmpty()) {
						while(stack.top().equals("+") || stack.top().equals("-")
								|| stack.top().equals("*")|| stack.top().equals("/")){
							queue.enqueue(stack.pop());
						}
					}
					stack.push(Character.toString(index));
				}
				
				//if given * or /, the only Operators that are of equal/higher precedence is those same Operators, so only need to 
				//check for those
				if(index == '*' || index == '/' ) {
					if(!stack.isEmpty()) {
						while(stack.top().equals("*")||stack.top().equals("/")) {
							queue.enqueue(stack.pop());
						}
					}
					stack.push(Character.toString(index));
				}
				
				//if given ), pop ops from top of stack and push to queue until last is (, if no ( then throw error
				if (index == ')') {
					//keep popping until string at top is (
					while(!stack.isEmpty() && !stack.top().equals("(")) 
						queue.enqueue(stack.pop());
					
					//checks whether stack isn't empty and string at top is (. if so, pop (
					if(!stack.isEmpty() && stack.top().equals("("))
						stack.pop();
					
					//otherwise, something is wrong. throw exception
					else
						throw new InvalidNotationFormatException();
				}		
			}
			
			//pop any remaining Operators from stack into queue
			while(!stack.isEmpty() && isOperator(stack.top()))
				queue.enqueue(stack.pop());
			
		//catch any random errors
		}catch(Exception e) {
			e.printStackTrace();
		}
		return queue.toString();
	}
	
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		MyStack<String> stack = new MyStack<String>(postfixExpr.length());
		String num1, num2;
		char[] copy = postfixExpr.toCharArray();
		
		try {
			for(char index : copy) {
				
				//if index is whitespace, skip over it
				if(index == ' ')
					continue;
				
				//if index is digit, push it to stack
				else if(Character.isDigit(index))
					stack.push(Character.toString(index));
				
				//if index is operator, pop the most recent two numbers from stack, and push the result of those numbers and 
				//the operator to the stack
				else if(isOperator(Character.toString(index))) {
					num2 = stack.pop();
					num1 = stack.pop();
					stack.push(Double.toString(operation(Character.toString(index), num1, num2)));
				}
			}
			//check whether stack has only one string, if not, throw exception
			if(stack.size() > 1) 
				throw new InvalidNotationFormatException();
			
			//check for any other exceptions
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Double.parseDouble(stack.toString());
	}

}
