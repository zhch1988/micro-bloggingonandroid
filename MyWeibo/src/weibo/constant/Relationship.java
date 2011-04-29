package weibo.constant;

/**
 * @author 郑璨
 */
public class Relationship {
	private User source;
	private User target;
	private boolean sourceFollowTarget; // source用户是否是target用户的粉丝
	private boolean targetFollowSource; // target用户是否是source用户的粉丝

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
