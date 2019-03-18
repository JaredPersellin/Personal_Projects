import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//Code snippet from the C- Semantic Analyzer

	private String reId(Stack<Character> stack, List<Token> tokens) {
		String[] keyWords = { "else", "if", "return", "while" };
		String[] keyWordTypes = { "void", "int", "float" };
		String id = "";
		char in = stack.pop();
		while (Character.isLetter(in)) {
			id += in;
			in = stack.pop();
		}
		stack.push(in);
		if (id.length() > 0) {
			if (isInArray(keyWords, id)) {
				tokens.add(new KeyWord(id));
			} else {
				if (isInArray(keyWordTypes, id)) {
					tokens.add(new TokenType(id));
				} else {
					tokens.add(new ID(id));
				}
			}
		}
		return id;
	}

	private String isANum(Stack<Character> stack, List<Token> tokens) {
		String id = "";
		char in = stack.pop();
		while (Character.isDigit(in)) {
			id += in;
			in = stack.pop();
		}
		stack.push(in);
		if (id.length() > 0) {
			tokens.add(new Num(id));
		}
		return id;
	}

	private String foundError(Stack<Character> stack, List<Token> tokens) throws IOException {
		String err = "";
		char in = stack.pop();
		while (!Character.isWhitespace(in) && !isSpecial(in)) {
			err += in;
			in = stack.pop();
		}
		stack.push(in);
		if (err.length() > 0) {
			tokens.add(new ErrorToken(err));
		}
		return err;
	}

	private String isSpecial(Stack<Character> stack, List<Token> tokens) throws IOException {
		String lotto = "*/<>=";
		String singleSpecs = "+-;,()[]{}";
		String[] commentSpecs = { "*/", "/*", "//" };
		String[] doubleSpecs = { "!=", "*/", "/*", "==", "<=", ">=", "//" };
		char inChar = stack.pop();
		String inString = "" + inChar;
		if (singleSpecs.indexOf(inChar) >= 0) {
			tokens.add(new SpecialChar(inString));
			return inString;
		}
		char inChar2 = stack.pop();
		String Char2S = inString + inChar2;
		if (isInArray(doubleSpecs, Char2S)) {
			if (!isInArray(commentSpecs, Char2S)) {
				tokens.add(new SpecialChar(Char2S));
			}
			return Char2S;
		}
		stack.push(inChar2);

		if (lotto.indexOf(inChar) >= 0) {
			tokens.add(new SpecialChar(inString));
			return inString;
		}

		tokens.add(new ErrorToken(inString));
		return inString;
	}

	private boolean isSpecial(char c) {
		if ("+-*/<>=!;,()[]{}".indexOf(c) >= 0) {
			return true;
		}
		return false;
	}

	private boolean isInArray(String[] array, String sample) {
		for (String s : array) {
			if (s.equals(sample)) {
				return true;
			}
		}
		return false;
	}

	private int pushOnStack(BufferedReader br, Stack<Character> stack) throws IOException {

		String line = br.readLine();
		if (line == null) {
			return 0;
		}
		stack.push('\n');
		for (int i = line.length() - 1; i >= 0; i--) {
			stack.push(line.charAt(i));
		}
		return line.length() + 1;
	}

	private Token nextT(List<Token> program) { 
		if (main.readSelect.currenT >= program.size()) {
			return new ErrorToken("Empty list");
		}
		return program.get(main.readSelect.currenT++);
	}

	public boolean readProgram(List<Token> program) {
		int count = 0;
		while (foundDec(program)) {
			++count;
			if (main.readSelect.currenT == program.size()) {
				break;
			}
		}
		if (count == 0) {
			return false;
		}
		return main.readSelect.currenT == program.size();
	}

	private boolean foundDec(List<Token> program) {
		int markked = main.readSelect.currenT;
		if (decChec(program) || functionDecChec(program)) {
			return true;
		}
		main.readSelect.currenT = markked;
		return false;
	}

	private boolean decChec(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (nextT(program) instanceof TokenType && nextT(program) instanceof ID
				&& ";".equals(nextT(program).toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		if (nextT(program) instanceof TokenType && nextT(program) instanceof ID
				&& "[".equals(nextT(program).toString()) && nextT(program) instanceof Num
				&& "]".equals(nextT(program).toString()) && ";".equals(nextT(program).toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean functionDecChec(List<Token> program) {
		int marked = main.readSelect.currenT;

		if (nextT(program) instanceof TokenType && nextT(program) instanceof ID
				&& "(".equals(nextT(program).toString()) && foundParam(program)
				&& ")".equals(nextT(program).toString()) && compoundChec(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean foundParam(List<Token> program) {
		int marked = main.readSelect.currenT;

		if ("void".equals(nextT(program).toString()) && ")".equals(nextT(program).toString())) {
			main.readSelect.currenT = main.readSelect.currenT -1;
			return true;
		}
		main.readSelect.currenT = marked;
		int counter = 0;
		while (readParam(program)) {
			counter++;
			if (!",".equals(nextT(program).toString())) {
				main.readSelect.currenT = main.readSelect.currenT -1;
				break;
			}
		}
		if (counter > 0 && ")".equals(nextT(program).toString())) {
			main.readSelect.currenT = main.readSelect.currenT -1;
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean readParam(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (nextT(program) instanceof TokenType && nextT(program) instanceof ID
				&& "[".equals(nextT(program).toString()) && "]".equals(nextT(program).toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		if (nextT(program) instanceof TokenType && nextT(program) instanceof ID) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean compoundChec(List<Token> program) {
		int marked = main.readSelect.currenT;
		if ("{".equals(nextT(program).toString())) {
			readLocalDeclarations(program);
			readStatementList(program);
			if ("}".equals(nextT(program).toString())) {
				return true;
			}
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean readLocalDeclarations(List<Token> program) {
		while (decChec(program)) {
		}
		return true;
	}

	private boolean readStatementList(List<Token> program) {
		while (readStatement(program)) {
		}
		return true;
	}

	private boolean readStatement(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (expressionChec(program) || compoundChec(program) || selectChec(program)
				|| iterateChec(program) || foundReturn(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean expressionChec(List<Token> program) {
		readExpression(program);
		int marked = main.readSelect.currenT;
		if (";".equals(nextT(program).toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean selectChec(List<Token> program) {
		int marked = main.readSelect.currenT;
		if ("if".equals(nextT(program).toString()) && "(".equals(nextT(program).toString())
				&& readExpression(program) && ")".equals(nextT(program).toString()) && readStatement(program)) {
			int marked2 = main.readSelect.currenT;
			if ("else".equals(nextT(program).toString())) {
				if (readStatement(program)) {
					return true;
				}
				main.readSelect.currenT = marked2;
				return false;
			}
			main.readSelect.currenT = marked2;
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean iterateChec(List<Token> program) {
		int marked = main.readSelect.currenT;
		if ("while".equals(nextT(program).toString()) && "(".equals(nextT(program).toString())
				&& readExpression(program) && ")".equals(nextT(program).toString()) && readStatement(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean foundReturn(List<Token> program) {
		int marked = main.readSelect.currenT;
		if ("return".equals(nextT(program).toString()) && ";".equals(nextT(program).toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		if ("return".equals(nextT(program).toString()) && readExpression(program)
				&& ";".equals(nextT(program).toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean readExpression(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (readVar(program) && "=".equals(nextT(program).toString()) && readExpression(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		if (expressChec(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean readVar(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (nextT(program) instanceof ID && "[".equals(nextT(program).toString()) && "]".equals(nextT(program).toString())
				&& readExpression(program)) {
			return true;
		}
		main.readSelect.currenT = marked;

		if (nextT(program) instanceof ID) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean expressChec(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (addExpresChec(program) && relationalChec(program) && addExpresChec(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		if (addExpresChec(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean relationalChec(List<Token> program) {
		int marked = main.readSelect.currenT;
		Token token = nextT(program);
		if (  "<".equals(token.toString()) || "<=".equals(token.toString()) || ">".equals(token.toString())
				|| "==".equals(token.toString()) || ">=".equals(token.toString()) || "!=".equals(token.toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean addExpresChec(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (readTerm(program) && addChec(program) && addExpresChec(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		if (readTerm(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean addChec(List<Token> program) {
		int marked = main.readSelect.currenT;
		Token token = nextT(program);
		if ("+".equals(token.toString()) || "-".equals(token.toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean readTerm(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (readFactor(program) && readMulop(program) && readTerm(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		if (readFactor(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean readMulop(List<Token> program) {
		int marked = main.readSelect.currenT;
		Token token = nextT(program);
		if ("*".equals(token.toString()) || "/".equals(token.toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean readFactor(List<Token> program) {
		int marked = main.readSelect.currenT;
		Token token = nextT(program);
		if (token instanceof Num) {
			return true;
		}
		main.readSelect.currenT = marked;
		if (readCall(program)) {
			return true;
		}
		main.readSelect.currenT = marked;

		if (readVar(program)) {
			return true;
		}
		main.readSelect.currenT = marked;
		if ("(".equals(nextT(program).toString()) && readExpression(program)
				&& ")".equals(nextT(program).toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean readCall(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (nextT(program) instanceof ID && "(".equals(nextT(program).toString()) && readArgs(program)
				&& ")".equals(nextT(program).toString())) {
			return true;
		}
		main.readSelect.currenT = marked;
		return false;
	}

	private boolean readArgs(List<Token> program) {
		int marked = main.readSelect.currenT;
		if (!readExpression(program)) {
			return true;
		}
		while (true) {
			Token token = nextT(program);
			if (")".equals(token.toString())) {
				main.readSelect.currenT = main.readSelect.currenT -1;
				return true;
			}
			boolean condition = ",".equals(token.toString()) && !readExpression(program);
			if (condition) {
				main.readSelect.currenT = marked;
				return false;
			}
		}
	}
}



