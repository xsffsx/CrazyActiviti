package org.crazyit.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class TransferInDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("转入银行接收款项...");
	}

}
