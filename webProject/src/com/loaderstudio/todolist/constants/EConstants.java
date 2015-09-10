package com.loaderstudio.todolist.constants;

public enum EConstants {
	
	TODAY {
		private String select = "SELECT tasks.id, tasks.usersId, tasks.description, tasks.date FROM tasks, users WHERE users.id = tasks.usersId AND users.login = ? AND date <= CURDATE() AND tasks.fix = '0' AND tasks.remove = '0'";

		@Override
		public String getSelect() {
			return select;
		}
	},
	TOMORROW {

		private String select = "SELECT tasks.id, tasks.usersId, tasks.description, tasks.date FROM tasks, users WHERE users.id = tasks.usersId AND users.login = ? AND date = DATE_ADD(CURDATE(), INTERVAL 1 DAY) AND tasks.fix = '0'";

		@Override
		public String getSelect() {
			return select;
		}
	},
	SOMEDAY {

		private String select = "SELECT tasks.id, tasks.usersId, tasks.description, tasks.date FROM tasks, users WHERE users.id = tasks.usersId AND users.login = ? AND date > DATE_ADD(CURDATE(), INTERVAL 1 DAY) AND tasks.fix = '0'";

		@Override
		public String getSelect() {
			return select;
		}
	},
	FIXED {

		private String select = "SELECT tasks.id, tasks.usersId, tasks.description, tasks.date FROM tasks, users WHERE users.id = tasks.usersId AND users.login = ? AND tasks.fix = '1'";

		@Override
		public String getSelect() {
			return select;
		}
	},
	RECYCLE {

		private String select = "SELECT tasks.id, tasks.usersId, tasks.description, tasks.date FROM tasks, users WHERE users.id = tasks.usersId AND users.login = ? AND tasks.remove = '1'";

		@Override
		public String getSelect() {
			return select;
		}
	},
	FIX {

		private String select = "UPDATE tasks SET fix = 1 WHERE id = ?";

		@Override
		public String getSelect() {
			return select;
		}
	},
	UNFIX {

		private String select = "UPDATE tasks SET fix = 0 WHERE id = ?";

		@Override
		public String getSelect() {
			return select;
		}
	},
	REMOVE {

		private String select = "UPDATE tasks SET remove = 1, fix = 0 WHERE id = ?";

		@Override
		public String getSelect() {
			return select;
		}
	},
	RESTORE {

		private String select = "UPDATE tasks SET remove = 0 WHERE id = ?";

		@Override
		public String getSelect() {
			return select;
		}
	},
	CLEAN {

		private String select = "DELETE FROM tasks WHERE id = ?";

		@Override
		public String getSelect() {
			return select;
		}
	};
	
	public abstract String getSelect();
	
}