//Ashna Razdan
//10/18/22
package Assignment3;

public class MultiplayerGame {
	private GameEntity turnTracker;
	private GameEntity[] index;

	// Constructors
	/**
	 * This constructor creates circularly doubly linked list
	 * 
	 * @param n - how many players are to be added
	 */
	public MultiplayerGame(int n) {
		if ((n < 1)) {
			throw new IllegalArgumentException("MultiplayerGame: n must be greater than 0");
		}
		this.index = new GameEntity[n];
		for (int i = 0; i < n - 1; i++) {
			index[i] = new GamePlayer(null, null, 0);
		}

		for (int i = 1; i < n; i++) {
			index[i] = new GamePlayer(index[i - 1], null, i);
			index[i - 1].next = index[i];
		}
		index[0].prev = index[n - 1];
		index[n - 1].next = index[0];

	}
    /**
     * This method the number of pieces in play
     * @return - the size of the list
     */
	public int size() {
		// size in game piece
		int count = 0;
		if (index[0] == null)
			return count;
		else {

			GameEntity current = index[0].next;

			while (current != index[0]) {
				count = count + current.size();
				current = current.next;
			}

		}
		return count;
	}
    /**
     * This method adds a new piece to the list
     * @param playerId - index of the array (player number)
     * @param name - piece name
     * @param strength - initial strength of the piece
     */
	public void addGamePiece(int playerId, String name, int strength) {
		if ((index.length < playerId) || (playerId < 0)) {
			throw new IllegalArgumentException("addGamePiece: no such player");
		}
		GameEntity current = index[playerId];
		while (!current.next.isGamePlayer()) {
			// check if it is a duplicate piece
			if (current.getName() == name) {
				throw new IllegalArgumentException("addGamePiece: duplicate entity");
			}
			current = current.next;
		}

		GamePiece newPiece = new GamePiece(current, current.next, name, strength);

		current.next = newPiece;
	};
    /**
     * This method removes a piece from the list
     * @param playerId - index of the array (player number)
     * @param name - piece name
     */
	public void removeGamePiece(int playerId, String name) {
		boolean loop1 = false;
		if ((index.length < playerId) || (playerId < -1)) {
			throw new IllegalArgumentException("removeGamePiece: no such player");
		}
		GameEntity current = index[playerId].next;
		GameEntity following = index[(playerId + 1) % index.length];
		while (current != following) {
			// check if piece exists
			if (current.getName() == name) {
				current.next.prev = current.prev;
				current.prev.next = current.next;
				loop1 = true;
				current = current.next;
			}

		}

		if (loop1 == false) {
			throw new IllegalArgumentException("removeGamePiece: entity does not exist");
		}

	};
    /**
     * This method iterates through list checks if piece name exists
     * @param name - piece name
     * @return true - piece exists
     */
	public boolean hasGamePiece(String name) {
		for (int i = 0; i < index.length - 1; i++) {
			GameEntity current = index[i].next;
			while (!current.isGamePlayer()) {
				if (current.getName() == name) {
					return true;
				}
				current = current.next;
			}
		}
		return false;
	};
    /**
     * This method removes all game pieces of a given player
     * @param playerId - index of the array (player number)
     */
	public void removeAllGamePieces(int playerId) {
		if ((index.length < playerId) || (playerId < -1)) {
			throw new IllegalArgumentException("removeAllGamePieces: no such player");
		}
		GameEntity current = index[playerId].next;
		while (!current.isGamePlayer()) {
			current.next.prev = current.prev;
			current.prev.next = current.next;
			current = current.next;
		}

	};
    /**
     * This method increases the strength of game pieces of a given player
     * @param playerId - index of the array (player number)
     * @param n - increase strength number
     */
	public void increaseStrength(int playerId, int n) {
		if ((index.length < playerId) || (playerId < -1)) {
			throw new IllegalArgumentException("increaseStrength: no such player");
		}
		GameEntity current = index[playerId];
		GameEntity temp = current.next;
		while (current.isGamePlayer()) {
			((GamePiece) temp).updateStrength(n);
			current = current.next;
		}
	};
    /**
     * This method creates string builder and appends them and prints out the list
     * @return s - string of all pieces
     */
	public String toString() {
		StringBuilder s = new StringBuilder();

		GameEntity current = index[0];
		s.append(current.toString());
		current = current.next;
		s.append("\n");
		while (current != index[0]) {
			s.append(current.toString());
			s.append("\n");
			current = current.next;
		}

		return s.toString();

	};
    /**
     * This method sets the turnTracker to the first GamePlayer.
     */
	public void initializeTurnTracker() {
		turnTracker = index[0];
	};
    /**
     * This method moves the turnTracker to the next GamePlayer.
     */
	public void nextPlayer() {
		turnTracker = turnTracker.next;
		while (!turnTracker.isGamePlayer()) {
			turnTracker = turnTracker.next;
		}
	};
    /**
     * This method moves the turnTracker to the next entity.
     */
	public void nextEntity() {
		turnTracker = turnTracker.next;
	};
    /**
     * This method moves the turnTracker to the previous GamePlayer.
     */
	public void prevPlayer() {
		turnTracker = turnTracker.prev;
		while (!turnTracker.isGamePlayer()) {
			turnTracker = turnTracker.prev;
		}
	};
    /**
     * This method creates a string to print turnTracker value
     */
	public String currentEntryToString() {
		return turnTracker.toString();
	};
/*
	public static void main(String[] args) {

		MultiplayerGame players = new MultiplayerGame(3);
		// players.addGamePiece(0, "yo", 5);
		// players.addGamePiece(0, "yi", 5);
		// players.addGamePiece(0, "yi", 5);
		// players.addGamePiece(1, "hi", 5);
		// System.out.println(players.toString());
		// players.addGamePiece(1, "zi", 5);
		players.addGamePiece(1, "yi", 5);
		// players.addGamePiece(1, "ye", 5);

		// players.removeAllGamePieces(0);
		// System.out.println(players.size());
		// players.increaseStrength(1, 6);
		// players.removeGamePiece(1, "hi");
		// players.removeGamePiece(1, "hi");
		System.out.println(players.toString());
		// System.out.println(players.toString());
		// System.out.println(players);
	};
	*/
}
