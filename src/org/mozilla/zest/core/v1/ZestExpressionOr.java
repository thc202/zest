/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.zest.core.v1;

import java.util.LinkedList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ZestExpressionOr.
 */
public class ZestExpressionOr extends ZestStructuredExpression {
	
	/**
	 * Main construptor.
	 *
	 */
	public ZestExpressionOr() {
		super();
	}

	/**
	 * Construptor.
	 *
	 * @param children the list of the OR clauses
	 */
	public ZestExpressionOr(List<ZestExpressionElement> children) {
		super(children);
	}

	/* (non-Javadoc)
	 * @see org.mozilla.zest.core.v1.ZestExpressionElement#isTrue(org.mozilla.zest.core.v1.ZestResponse)
	 */
	@Override
	public boolean isTrue(ZestResponse response) {
		boolean toReturn = false;
		for (ZestExpressionElement con : getChildrenCondition()) {
			toReturn = toReturn || con.evaluate(response);// compute OR for each
															// child
			if (toReturn) {
				break;// lazy evaluation
			}
		}
		return toReturn;
	}

	/* (non-Javadoc)
	 * @see org.mozilla.zest.core.v1.ZestExpression#deepCopy()
	 */
	@Override
	public ZestExpressionOr deepCopy() {
		List<ZestExpressionElement> copyChildren = new LinkedList<>();
		if (getChildrenCondition()!=null) {
			for (int i = 0; i < getChildrenCondition().size(); i++) {
				copyChildren.add(getChildrenCondition().get(i).deepCopy());
			}
		}
		ZestExpressionOr copy=new ZestExpressionOr(copyChildren);
		return copy;
	}
}