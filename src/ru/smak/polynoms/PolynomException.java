package ru.smak.polynoms;

public class PolynomException extends Throwable {
	private String[] msg = {
		"OK",
		"Количество узлов и количество значений в узлах различны!",
		"Попытка создания полинома Лагранжа по пустому набору значений!"

	};
	private int errnum = 0;
	PolynomException(int i) {
		errnum = Math.abs(i) % msg.length;
	}

	@Override
	public String getMessage(){
		return msg[errnum];
	}
}
