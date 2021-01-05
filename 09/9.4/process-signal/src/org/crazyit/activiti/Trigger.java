package org.crazyit.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * 向执行注发送信号
 * 
 * @author yangenxiong
 * 
 */
public class Trigger {

	public static void main(String[] args) {
		// 创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 得到流程存储服务实例
		RepositoryService repositoryService = engine.getRepositoryService();
		// 得到运行时服务组件
		RuntimeService runtimeService = engine.getRuntimeService();
		// 部署流程描述文件
		repositoryService.createDeployment()
				.addClasspathResource("bpmn/trigger.bpmn").deploy();
		// 开始流程
		ProcessInstance pi = runtimeService
				.startProcessInstanceByKey("vacationRequest");
		// 查找执行流（当前只有一个执行流）
		Execution exe = runtimeService.createExecutionQuery().activityId("receivetask1").singleResult();
		if (exe != null) {
			System.out.println("当前流程节点为： receivetask1");
		}
		// 触发等待节点
		runtimeService.trigger(exe.getId());		
		// 查询当前的流程节点
		exe = runtimeService.createExecutionQuery().activityId("usertask1").singleResult();
		if (exe != null) {
			System.out.println("当前流程节点为： usertask1");
		}
	}
}
