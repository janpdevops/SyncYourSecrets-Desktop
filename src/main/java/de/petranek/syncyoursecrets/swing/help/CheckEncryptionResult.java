/**
 * SyncYourSecrets-SWT packages the functionality of
 * SyncYourSecrets into a SWT product and provides a
 * a graphical user interface
 *
 *
 *    Copyright 2009 Jan Petranek
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.petranek.syncyoursecrets.swing.help;

/**
 * The Class EncryptionTestResult is a simple result object. Its information is
 * later used to build the message box. I resolved to this solution, as the icon
 * for the MessageBox can only be set at its creation time.
 */
public class CheckEncryptionResult {

	/** The title that will be set for the dialog box. */
	private String title;
	/** The message, should be informative. */
	private StringBuilder message = new StringBuilder();
	/** One of the message style allowed for MessageBox. */
	private int messageStyle;

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the message style.
	 * 
	 * @return the message style
	 */
	int getMessageStyle() {
		return messageStyle;
	}

	/**
	 * Sets the message style, must be an allowed style for MessageBox.
	 * 
	 * @param messageStyle
	 *            the new message style
	 */
	void setMessageStyle(int messageStyle) {
		this.messageStyle = messageStyle;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	String getMessage() {
		return message.toString();
	}

	/**
	 * Append a string to the message.
	 * 
	 * @param s
	 *            the message to append to.
	 */
	void append(String s) {
		message.append(s);
	}

}
