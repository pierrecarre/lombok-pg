/*
 * Copyright © 2011 Philipp Eichhorn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lombok.eclipse.handlers.ast;

import static lombok.eclipse.handlers.Eclipse.setGeneratedByAndCopyPos;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.eclipse.EclipseNode;

import org.eclipse.jdt.internal.compiler.ast.ASTNode;
import org.eclipse.jdt.internal.compiler.ast.BinaryExpression;
import org.eclipse.jdt.internal.compiler.ast.Expression;
import org.eclipse.jdt.internal.compiler.ast.OperatorIds;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class BinaryBuilder implements ExpressionBuilder<BinaryExpression> {
	private final String operator;
	private final ExpressionBuilder<? extends Expression> left;
	private final ExpressionBuilder<? extends Expression> right;

	@Override
	public BinaryExpression build(EclipseNode node, ASTNode source) {
		final int opCode;
		if ("+".equals(operator)) {
			opCode = OperatorIds.PLUS;
		} else if ("-".equals(operator)) {
			opCode = OperatorIds.MINUS;
		} else if ("*".equals(operator)) {
			opCode = OperatorIds.MULTIPLY;
		} else if ("/".equals(operator)) {
			opCode = OperatorIds.DIVIDE;
		} else {
			opCode = 0;
		}
		BinaryExpression binaryExpression = new BinaryExpression(left.build(node, source), right.build(node, source), opCode);
		setGeneratedByAndCopyPos(binaryExpression, source);
		return binaryExpression;
	}
}