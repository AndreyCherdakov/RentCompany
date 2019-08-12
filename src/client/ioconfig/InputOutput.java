package client.ioconfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface InputOutput {
	
	String inputString(String prompt);
	void output(Object obj);
	void outputErrors(Object obj);
	
default void outputLine(Object obj) {
	output(obj.toString() + "\n");
}
default void outputErrorLine(Object obj) {
	outputErrors(obj.toString() + "\n");
}
default Integer inputInteger(String prompt) {
	return inputObject(prompt, LocalDateTime.now() + ": ERROR - it's not a number",s-> {
		try {
			Integer res = Integer.parseInt(s);
			return res;
		} catch (NumberFormatException e) {
			return null;
		}
	});
}
default Integer inputInteger(String prompt,Integer minValue,Integer maxValue) {
		return inputObject(prompt,String.format(LocalDateTime.now() + ": ERROR - It's not number in range [%d-%d]",
				minValue,maxValue),s-> {
			try {
				Integer res=Integer.parseInt(s);
				return res>=minValue&&res<=maxValue?res:null;
			} catch (NumberFormatException e) {
				return null;
			}
		});
}
default Double inputDouble(String prompt) {
	return inputObject(prompt,LocalDateTime.now() + ": ERROR - It's not  number",
			s->{
				try {
					Double number = Double.parseDouble(s);
					return number;
				} catch (NumberFormatException e) {
					return null;
				}
			});
}
default Long inputLong(String prompt) {
	return inputObject(prompt,LocalDateTime.now() + ": ERROR - It's not integer(long) number",
			s->{
				try {
					Long number=Long.parseLong(s);
					return number;
				} catch (NumberFormatException e) {
					return null;
				}
			});
}
default Long inputLongByPattern(String prompt, Pattern pattern) {
	return inputObject(prompt,LocalDateTime.now() + ": ERROR - does not match the pattern. Pattern - " + pattern,
			s->{
				Matcher phoneMatcher = pattern.matcher(s);
				if (phoneMatcher.matches()) {
					try {
						Long number=Long.parseLong(s);
						return number;
					} catch (NumberFormatException e) {
						return null;
					}
				}
				return null;
			});
}
default String inputString(String prompt,List<String> options) {
	return inputObject(prompt,LocalDateTime.now() + ": ERROR - Not in options",
			s->options.contains(s)?s:null);
}
default LocalDate inputDate(String prompt,String format) {
	return inputObject(prompt, LocalDateTime.now() + ": ERROR - Wrong date " + format ,
			s->{
				try {
					DateTimeFormatter dtf=
						DateTimeFormatter.ofPattern(format);
					return LocalDate.parse(s, dtf);
				} catch (DateTimeParseException e) {
					return null;
				}
			});
}
default <R> R inputObject(String prompt,String errorPrompt,Function<String,R> mapper) {
	while (true) {
		String text = inputString(prompt);
		if (text == null)
			return null;
		R res = mapper.apply(text);
		if (res != null)
			return res;
		outputErrorLine(errorPrompt);
	}
}
default String inputStringByPattrn(String prompt, Pattern pattern) {
	return inputObject(prompt, LocalDateTime.now() + ": ERROR - does not match the pattern. Pattern - " + pattern,
			s->{
				Matcher phoneMatcher = pattern.matcher(s);
				if (phoneMatcher.matches()) {
					return s;
				}
				return null;
			});
}
}
