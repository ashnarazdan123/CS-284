package Assignment3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplayerGameTest {
	@Test
	// MultiplayerGame(int n)
	void MultiplayerGameTest1() {
		MultiplayerGame players = new MultiplayerGame(3);
		assertEquals("Player0\nPlayer1\nPlayer2\n", players.toString());
	}

	@Test
	// public int size()
	void SizeTest() {
		MultiplayerGame players = new MultiplayerGame(4);
		players.addGamePiece(0, "yo", 5);
		players.addGamePiece(1, "hi", 5);
		players.addGamePiece(1, "zi", 5);
		players.addGamePiece(2, "yi", 5);
		players.addGamePiece(2, "si", 5);
		players.addGamePiece(3, "gi", 5);
		assertEquals(6, players.size());
	}

	@Test
	// public void addGamePiece(int playerId, String name, int strength)
	void addGamePieceTest() {
		MultiplayerGame players = new MultiplayerGame(3);
		players.addGamePiece(0, "yo", 5);
		assertEquals("Player0\n" + "GamePiece: yo strength: 5\n" + "Player1\n" + "Player2\n", players.toString());
	}
	@Test
	//public void removeGamePiece(int playerId, String name)
	void removeGamePieceTest() {
		MultiplayerGame players = new MultiplayerGame(3);
		players.addGamePiece(0, "yi", 5);
		//System.out.println(players.toString());
		players.removeGamePiece(0, "yi");
		//System.out.println(players.toString());
		assertEquals("Player0\n"  + "Player1\n" + "Player2\n", players.toString());
	}
	
	@Test
	//public void hasGamePiece( String name)
	void hasGamePieceTest() {
		MultiplayerGame players = new MultiplayerGame(3);
		players.addGamePiece(1, "yi", 5);
		assertEquals(true,players.hasGamePiece("yi"));
	}
	
	@Test
	//removeAllGamePieces(int playerId)
	void removeAllTest() {
		MultiplayerGame players = new MultiplayerGame(3);
		players.addGamePiece(1, "yi", 5);
		players.addGamePiece(1, "yo", 5);
		players.removeAllGamePieces(1);
		assertEquals("Player0\nPlayer1\nPlayer2\n",players.toString());
	}
	
	@Test
	//public void increaseStrength(int playerId, int n) 
	void increaseStrengthTest() {
		MultiplayerGame players = new MultiplayerGame(3);
		players.addGamePiece(1, "yi", 5);
		players.increaseStrength(1,6);
		assertEquals("Player0\nPlayer1\nGamePiece: yi strength: 11\nPlayer2\n",players.toString());
	}
	
	@Test
	//public void initializeTurnTracker() 
	void initializeTTTest() {
		MultiplayerGame players = new MultiplayerGame(3);
		players.initializeTurnTracker();
		assertEquals("Player0",players.currentEntryToString());
	}
	
	@Test
	//public void nextPlayer() 
	void nextPlayerTest() {
		MultiplayerGame players = new MultiplayerGame(3);
		players.initializeTurnTracker();
		players.nextPlayer();
		assertEquals("Player1",players.currentEntryToString());
	}
	@Test
	//public void nextEntity() 
	void nextEntityTest() {
		MultiplayerGame players = new MultiplayerGame(3);
		players.addGamePiece(0, "yi", 5);
		players.initializeTurnTracker();
		players.nextEntity();
		assertEquals("GamePiece: yi strength: 5",players.currentEntryToString());
	}
	
	@Test
	//public void prevPlayer() 
	void prevPlayerTest() {
		MultiplayerGame players = new MultiplayerGame(3);
		players.initializeTurnTracker();
		players.nextPlayer();
		players.nextPlayer();
		players.prevPlayer();
		assertEquals("Player1",players.currentEntryToString());
	}
	
	

}
