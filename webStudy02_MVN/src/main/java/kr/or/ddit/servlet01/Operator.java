package kr.or.ddit.servlet01;

@FunctionalInterface
public interface Operator {
	public long operate(int leftOp, int rightOp);
}
