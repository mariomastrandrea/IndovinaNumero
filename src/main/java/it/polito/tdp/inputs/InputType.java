package it.polito.tdp.inputs;

import it.polito.tdp.messages.Message;

public interface InputType
{
	boolean isValid();
	Message getMessage();
}
