package test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ou3.Event;
import ou3.Network;
import ou3.Node;
import ou3.Route;

public class NodeTest {

	private Node node;
	
	@Before 
	/*
	 * A setup class to initialize necessary 
	 * parameters in order to run the tests.
	 */
	public void setupNetwork()
	{
        Node.setAgentProbability((float) 0.5 );
        Node.setEventProbability((float)  0.0001 );
		this.node = new Node( 3, 3 );

	}
	
	@Test
	/* 
	 * The following test was made to ensure 
	 * that an event could be made and as followed
	 * a agent message. 
	 * The test was generated by running the
	 * probability of 10 times the chance.
	 * The expected result is that 0-1 events
	 * was made per 10'000 steps so as a whole
	 * 5 to 15 events is expected to occur. 
	 */
	public void CreateEventAndAgentTest()
	{
		for(int i = 0; i<10000*10; i++)
		{
			this.node.tryCreateEvent(i);
		}
		int total = this.node.getEvents().size();
		assertTrue("Is the totalt number of events as expected?",total > 5 && total < 15);
		int CurrentID = 0;
        for ( Event e : this.node.getEvents())
        {
        	// Is the id incremented in correct order?
        	assertTrue("Is the event id incremeted based on a new event?:",e.getID() == CurrentID++);
        	// Is the event in the routing table?
        	assertTrue("Is the event in the routing table?:",this.node.getRoutingTable().containsKey(e.getID()));
        }
	}
	
	
	
	
	
	
	
	
}
