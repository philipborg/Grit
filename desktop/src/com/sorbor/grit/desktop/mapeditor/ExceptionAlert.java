package com.sorbor.grit.desktop.mapeditor;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class ExceptionAlert {

	public static void alertException(Exception e, String header) {
		alertException(e, header, e.getLocalizedMessage());
	}

	public static void alertException(Exception e, String header, String content) {
		try {
			// Create alert window
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(header);
			alert.setContentText(content);

			// Generate exception string
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			// Generate text area
			TextArea stackTraceTextArea = new TextArea(sw.toString());
			stackTraceTextArea.setEditable(false);
			stackTraceTextArea.setWrapText(true);
			alert.getDialogPane().setExpandableContent(stackTraceTextArea);
			
			alert.showAndWait();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("Something went horribly wrong...");
			System.exit(1);
		}
	}

	public static void alertException(Exception e) {
		alertException(e, e.getLocalizedMessage(), null);
	}

}
