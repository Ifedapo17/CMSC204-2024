import java.util.*;

public class Notation {
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		MyStack<String> postfix = new MyStack<>();
		ArrayList<Character> lowerOperators = new ArrayList<>();
		ArrayList<Character> higherOperators = new ArrayList<>();
		
		lowerOperators.add('-');
		lowerOperators.add('+');
		
		higherOperators.add('*');
		higherOperators.add('/');
		higherOperators.add('%');
		
		postfixExpr.replace("\\s", "");
		
		if(postfixExpr.length() < 3) {
			throw new InvalidNotationFormatException("A valid postfix length is greater than or equal to 3");
		}
		
		validPostFix(postfixExpr);
		
		try {
			for(int i = 0; i < postfixExpr.length(); i++) {
				if(Character.isDigit(postfixExpr.charAt(i)) || (postfixExpr.charAt(i) == '(')) {
					postfix.push(Character.toString(postfixExpr.charAt(i)));
				}else if(lowerOperators.contains(postfixExpr.charAt(i)) || higherOperators.contains(postfixExpr.charAt(i))){
					if(postfix.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					
					double num1 = Double.parseDouble(postfix.pop()),
							num2 = Double.parseDouble(postfix.pop()),
							result;
					
					switch(postfixExpr.charAt(i)) {
						case '+':
							result = num2 + num1;
							break;
						case '-':
							result = num2 - num1;
							break;
						case '*':
							result = num2 * num1;
							break;
						case '/':
							result = num2 / num1;
							break;
						case '%':
							result = num2 % num1;
							break;
						default: throw new InvalidNotationFormatException();
						}
					
					postfix.push(String.valueOf(result));
				}else {
					throw new InvalidNotationFormatException();
				}
			}
			
			if(postfix.size() != 1) {
				throw new InvalidNotationFormatException();
			}
			return Double.valueOf(postfix.top());
		}catch(StackOverflowException | StackUnderflowException | NumberFormatException e) {
			throw new InvalidNotationFormatException();
		}
	}
	
	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix - the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
		MyStack<String> operatorsAndBrackets = new MyStack<>();
		ArrayList<Character> lowerOperators = new ArrayList<>();
		ArrayList<Character> higherOperators = new ArrayList<>();
		String infix;
		
		lowerOperators.add('-');
		lowerOperators.add('+');
		
		higherOperators.add('*');
		higherOperators.add('/');
		higherOperators.add('%');
		
		postfix.replace("\\s", "");
		
		if(postfix.length() < 3) {
			throw new InvalidNotationFormatException("A valid postfix length is greater than or equal to 3");
		}
		
		validPostFix(postfix);
		
		try {
			for(int i = 0; i < postfix.length(); i++) {
				if(Character.isDigit(postfix.charAt(i))) {
					operatorsAndBrackets.push(Character.toString(postfix.charAt(i)));
				}else if(lowerOperators.contains(postfix.charAt(i)) || 
						higherOperators.contains(postfix.charAt(i))) {
					if(operatorsAndBrackets.size() < 2){
						throw new InvalidNotationFormatException();
					}
					
					String firstVar = operatorsAndBrackets.pop(),
							secondVar = operatorsAndBrackets.pop(),
							temp = "(" + secondVar + postfix.charAt(i) + firstVar + ")";
						
					operatorsAndBrackets.push(temp);
				}
			}
			
			if(operatorsAndBrackets.size() != 1) {
				throw new InvalidNotationFormatException();
			
			}
			
			return infix = operatorsAndBrackets.pop();
		}catch(StackOverflowException | StackUnderflowException e) {
			throw new InvalidNotationFormatException(e.getMessage());
		}
	}
	
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix - the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException - if the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
		MyQueue<String> postfix = new MyQueue<String>();
		MyStack<String> operatorsAndBrackets = new MyStack<>();
		ArrayList<Character> lowerOperators = new ArrayList<>();
		ArrayList<Character> higherOperators = new ArrayList<>();
		String finalInfix;
		
		lowerOperators.add('-');
		lowerOperators.add('+');
		
		higherOperators.add('*');
		higherOperators.add('/');
		higherOperators.add('%');
		
		infix.replace("\\s", "");
		
		if(infix.length() < 3) {
			throw new InvalidNotationFormatException("A valid infix length is greater than or equal to 3");
		}
		
		try {
			for(int i = 0; i < infix.length(); i++) {
				if(Character.isDigit(infix.charAt(i))) {
					postfix.enqueue(Character.toString(infix.charAt(i)));
				}else if(infix.charAt(i) == '(') {
					operatorsAndBrackets.push(Character.toString(infix.charAt(i)));
				}else if(lowerOperators.contains(infix.charAt(i)) || 
						higherOperators.contains(infix.charAt(i))) {
					while(!operatorsAndBrackets.isEmpty() && precedence(operatorsAndBrackets.top()) >= precedence(Character.toString(infix.charAt(i)))) {
						postfix.enqueue(operatorsAndBrackets.pop());
					}
					
					operatorsAndBrackets.push(Character.toString(infix.charAt(i)));
				}else if(infix.charAt(i) == ')') {
					while((!operatorsAndBrackets.isEmpty()) && (operatorsAndBrackets.top().charAt(0) != '(')) {
						String popped = operatorsAndBrackets.pop();
						
						if(popped.charAt(0) != ')') {
							postfix.enqueue(popped);
						}
					}
					
					if((!operatorsAndBrackets.isEmpty()) && (operatorsAndBrackets.top().charAt(0) == '(')) {
						operatorsAndBrackets.pop();
					}else {
						throw new InvalidNotationFormatException("No left parenthesis found that "
								+ "needs to be discarded");
					}
				}else {
					throw new InvalidNotationFormatException("Invalid character in the infix");
				}
			}
			
			while(!operatorsAndBrackets.isEmpty()) {
				postfix.enqueue(operatorsAndBrackets.pop());
			}
			
			validPostFix(postfix.toString());
			
			return postfix.toString();
		}catch(QueueOverflowException | StackOverflowException |StackUnderflowException e) {
			throw new InvalidNotationFormatException(e.getMessage());
		}
	}
	
	/**
	 * This is to test if a post fix notation is valid
	 * @param arithmeticPostfixExpression
	 * @throws InvalidNotationFormatException
	 */
	private static void validPostFix(String arithmeticPostfixExpression) throws
	InvalidNotationFormatException{
		ArrayList<Character> operators = new ArrayList<>();
		
		operators.add('+');
		operators.add('-');
		operators.add('/');
		operators.add('*');
		operators.add('%');
		
		int operatorNum = 0,
				operandNum = 0;
		
		arithmeticPostfixExpression = arithmeticPostfixExpression.replaceAll("\\s", "");
		
		if(arithmeticPostfixExpression.length() < 3) {
			throw new InvalidNotationFormatException("An infix arithmetic expression must have more"
					+ " than 3 characters");
		}
		
		for(int i = 0; i < arithmeticPostfixExpression.length(); i++) {
			if((!Character.isDigit((arithmeticPostfixExpression.charAt(i))) && 
					(!operators.contains(arithmeticPostfixExpression.charAt(i))))) {
				throw new InvalidNotationFormatException("A postfix arithmetic expression must "
						+ "have at least two numbers and one operator");
			}else if((i < 2) && !Character.isDigit(arithmeticPostfixExpression.charAt(i))) {
				throw new InvalidNotationFormatException("A postfix arithmetic expression must "
						+ "have double numbers");
			}else if((i == (arithmeticPostfixExpression.length() - 1)) && 
					(!operators.contains(arithmeticPostfixExpression.charAt(i)))) {
				throw new InvalidNotationFormatException("A postfix arithmetic expression must "
						+ "have an operator as the last character");
			}
			
			if(operators.contains(arithmeticPostfixExpression.charAt(i))) {
				++operatorNum;
			}else if(Character.isDigit(arithmeticPostfixExpression.charAt(i))) {
				++operandNum;
			}
			
			/* OUT OF PLACE
			if((operandNum - 1) != operatorNum) {
				throw new InvalidNotationFormatException("In a postfix arithmetic operator the "
						+ "count of operators must be 1 greater than the count of operands");
			}*/
		}
		
		// SHOULD BE HERE
		if((operandNum - 1) != operatorNum) {
			throw new InvalidNotationFormatException("In a postfix arithmetic operator the "
					+ "count of operators must be 1 greater than the count of operands");
		}
	}
	
	/**
	 * 
	 * @param operators
	 * @return 1, if it is a lower precedence operator
	 * @return 2, if it is a higher precedence operator
	 * @return -1 if no operator
	 */
	public static int precedence(String operators) {
		switch(operators) {
			case("+"):
			case("-"):
				return 1;
			case("*"): 
			case("/"): 
			case("%"):
				return 2;
			default: break;
		}
		
		return -1;
	}
}
