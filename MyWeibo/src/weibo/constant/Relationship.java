package weibo.constant;

/**
 * @author ֣�
 */
public class Relationship {
	private User source;
	private User target;
	private boolean sourceFollowTarget; // source�û��Ƿ���target�û��ķ�˿
	private boolean targetFollowSource; // target�û��Ƿ���source�û��ķ�˿

	public User getSource() {
		return source;
	}

	public void setSource(User source) {
		this.source = source;
	}

	public User getTarget() {
		return target;
	}

	public void setTarget(User target) {
		this.target = target;
	}

	public boolean getSourceFollowTarget() {
		return sourceFollowTarget;
	}

	public void setSourceFollowTarget(boolean sourceFollowTarget) {
		this.sourceFollowTarget = sourceFollowTarget;
	}

	public boolean getTargetFollowSource() {
		return targetFollowSource;
	}

	public void setTargetFollowSource(boolean targetFollowSource) {
		this.targetFollowSource = targetFollowSource;
	}

}
