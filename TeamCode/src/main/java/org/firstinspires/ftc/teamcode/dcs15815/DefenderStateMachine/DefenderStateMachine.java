package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;


import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous.BuzzBotAutonomousOpMode;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;


public class DefenderStateMachine {

	private DefenderState currentState;
	public DefenderBot bot;
	private boolean isFinished = false;
	private boolean isDoneWithInit = false;
	public BuzzBotAutonomousOpMode opMode = null;
	public boolean isDebugging = false;

	public ArrayList<DefenderState> initStates;


	public DefenderStateMachine() {
		isFinished = false;
		initStates = new ArrayList<DefenderState>();
	}

	public DefenderStateMachine(DefenderBot b) {
//		eventSources = new ArrayList<>();
		bot = b;
		isFinished = false;
		initStates = new ArrayList<DefenderState>();
//		currentEvents = new ArrayList<>();
	}

	public DefenderStateMachine(DefenderBot b, BuzzBotAutonomousOpMode om) {
//		eventSources = new ArrayList<>();
		bot = b;
		opMode = om;
		isFinished = false;
		initStates = new ArrayList<DefenderState>();
//		currentEvents = new ArrayList<>();
	}


	public DefenderState startWithState(DefenderState s) {
		setState(s);
		return s;
	}

	public DefenderStateMachine setBot(DefenderBot b) {
		bot = b;
		return this;
	}

	public DefenderStateMachine setOpMode(BuzzBotAutonomousOpMode om) {
		this.opMode = om;
		this.bot = opMode.bot;
		return this;
	}

	public DefenderStateMachine setStartingPosition(DefenderBotPosition position) {
		if (bot != null) {
			SetCurrentPositionState startingPositionState = SetCurrentPositionState.where(position);
		} else {
			throw new RuntimeException("State machine has no bot. Cannot set starting position without a bot.");
		}
		return this;
	}

//	public void addEventSource(DefenderEventSource s) {
//		eventSources.add(s);
//	}

	public boolean isFinished() {
		return isFinished;
	}

//	public ArrayList<DefenderEvent> getCurrentEvents() {
//		return currentEvents;
//	}


	public void setState(DefenderState s) {
		if (currentState != null) {
			currentState.beforeStop();
		}
		currentState = s;
		if (isDebugging) {
			if (currentState.stateLabel != null) {
				addTelemetryLine("Setting: " + currentState.stateLabel);
			}
		}
		currentState.beforeStart();
		currentState.setStateMachine(this);
	}

	public void setNextState(DefenderState s) {
		currentState.nextState = s;
	}

//	public void setVariable(String key, DefenderStateVariable v) {
//		variables.put(key, v);
//	}
//
//	public DefenderStateVariable getVariable(String key) {
//		return variables.get(key);
//	}

	public DefenderState getCurrentState() {
		return currentState;
	}

	public DefenderStateMachine setDebugging(boolean b) {
		isDebugging = b;
		return this;
	}

	public void run() {
//		ArrayList<DefenderEvent> events = new ArrayList<>();

		if (!isDoneWithInit) {
			// All states to run during init should be single-run = no loops; we're not built to loop through them here
			for (DefenderState s : initStates) {
				s.setIsStarted(true);
				s.run();
			}
			isDoneWithInit = true;
		}

		if (isFinished || !opMode.opModeIsActive()) {
			return;
		}
		if (currentState == null) {
			throw new RuntimeException("State machine has no current state. Did you forget to set a first state?");
		}

		if (opMode == null) {
			throw new RuntimeException("State machine has no opmode. Did you forget to set it?");
		}

//		for (DefenderEventSource s : eventSources) {
//			events.addAll(s.gatherEvents());
//		}
//		currentEvents.clear();
//		currentEvents.addAll(events);
		currentState.setIsStarted(true);
		currentState.run();

		if (currentState.isFinished()) {
			if (currentState.hasNextState()) {
				if (isDebugging) {
					addTelemetryLine("Next state: " + (currentState.nextState.stateLabel != null ? currentState.nextState.stateLabel : "n/a"));
				}
				setState(currentState.nextState);
			} else {
				isFinished = true;
			}
		}
	}

	public void addTelemetryLine(String x) {
		opMode.telemetry.addLine(x);
		opMode.telemetry.update();
	}

}
