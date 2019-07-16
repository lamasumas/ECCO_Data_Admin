package com.adminTool.DatabaseDocuments;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class QuestionsBeginner {
		
		@Id
		private String id;
		private String question;
		private String[] answers;
		
		
		public QuestionsBeginner(String question, String[] answers)
		{
			this.question = question;
			this.answers = answers;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String[] getAnswers() {
			return answers;
		}

		public void setAnswers(String[] answers) {
			this.answers = answers;
		}
}
