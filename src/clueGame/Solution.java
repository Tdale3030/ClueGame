package clueGame;

public class Solution {
	public Card person;
	public Card room;
	public Card weapon;
	
	
	public Solution(Card person, Card room, Card weapon) {
		super();
		this.person = person;
		this.room = room;
		this.weapon = weapon;
	}


	public Card getRoom() {
		// TODO Auto-generated method stub
		return room;
	}


	public Card getWeapon() {
		// TODO Auto-generated method stub
		return weapon;
		
	}


	public Card getPerson() {
		// TODO Auto-generated method stub
		return person;
	}


	public void setPerson(Card person) {
		this.person = person;
	}


	public void setRoom(Card room) {
		this.room = room;
	}


	public void setWeapon(Card weapon) {
		this.weapon = weapon;
	}
	
	
	
}
