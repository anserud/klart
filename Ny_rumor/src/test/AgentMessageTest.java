package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import ou3.AgentMessage;
import ou3.Event;
import ou3.Network;
import ou3.Node;
import ou3.Route;

public class AgentMessageTest {

	private Network net;
	private Node node1;
	private Node node2;
	private AgentMessage am;
	
	@Before 
	/*
	 * A setup class to initialize necessary 
	 * parameters in order to run the tests.
	 */
	public void setupNetwork()
	{
		Event.resetCurrentID();
		net = new Network( "H:\\git\\New folder\\klara\\Ny_rumor\\src\\configuration.xml" );
		this.node1 = new Node( 0, 0 );
		this.node2 = new Node( 1, 0 );
		
		for(int i = 0; i<10000*5; i++)
		{
			if(this.node1.getEvents().size() > 0) break;
			this.node1.tryCreateEvent(i);
		}
		for(int i = 0; i<10000*5; i++)
		{
			if(this.node2.getEvents().size() > 0) break;
			this.node2.tryCreateEvent(i);
		}
		
	}
	
	@Test
	/*
	 * The following test was made to ensure
	 * that a agent message could be created 
	 * by passing in a node as its orgin.
	 */
	public void createAgentMessage()
	{
		this.am = new AgentMessage( node1 );
		assertTrue("Is the current position (node) set?:",this.am.getPoint() != null);
		assertTrue("Is the position of the node in the right place?:",this.am.getPoint().distance(new Point(0,0)) == 0);
	}
	
	@Test
	/*
	 * The following test was made to 
	 * ensure that a agents messages time to live
	 * was successfully decrement.
	 */
	public void decrementTTL()
	{
		this.am = new AgentMessage( node1 );
		int lifeLength = this.am.getLifeLength();
		for(int i = 0; i < lifeLength-1; i++)
		{
			assertTrue("Is the life length stil not zero?:",this.am.stepUpdate());
		}
		// TTL = 0
		assertTrue("Is the life length now zero?:",this.am.stepUpdate() == false);
	}
	
	@Test
	/*
	 * The following test was made to ensure that
	 * a agent message could syncronize with nodes
	 * by updating its own table and the node table it 
	 * currently positioned at.
	 */
	public void syncronizeTableTest()
	{
		Route route;
		this.am = new AgentMessage( node1 );
		
		this.am.syncroniseTable( this.node1.getRoutingTable() );
		assertTrue("Is the route table of size of one?:",this.am.getRoutingTable().size() == 1);
		
		// Is the node in route table the one added?
		route = this.am.getRoutingTable().get(0);
		assertTrue("Is the node in the list correct?:",route.getDirection() == node1);
		
		// Is the route table of size 2?
		this.am.syncroniseTable( this.node2.getRoutingTable() );
		assertTrue("Does the list now contains two nodes?:",this.am.getRoutingTable().size() == 2);
		
		// Is the nodes in the new route table 
		// the first and new node?
		route = this.am.getRoutingTable().get(0);
		assertTrue("Is the first node in the list correct?:",route.getDirection() == node1);
		route = this.am.getRoutingTable().get(1);
		assertTrue("Is last node in the list correct?:",route.getDirection() == node2);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
