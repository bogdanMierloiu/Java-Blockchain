# Blockchain for Voting

- This project demonstrates a blockchain-based voting system implemented in Java. It allows users to add candidates, cast
votes, and ensures the integrity of the voting process through blockchain technology.

## Features
- Candidate Management: Users can add candidates for the election.
- Voting: Registered users can cast their votes securely.
- Blockchain Integrity: Utilizes blockchain to ensure the security and integrity of the voting process.
- Mining: Implements a mining process to validate and add new blocks to the blockchain.

## How It Works
- Initialization: Start by initializing the blockchain with a name, typically denoting the purpose or context of the
  voting process.
- Genesis Block: Create the genesis block, which serves as the initial block in the blockchain.
- Adding Candidates: Add candidates to the system to enable voting.
- Casting Votes: Registered users can cast their votes for the desired candidates.
- Mining Process: The mining process ensures that each vote is securely added to the blockchain.

## Getting Started
Follow these steps to get the application up and running:

- Database Setup: Configure the relational database named "blockchain" to store blockchain data.
- Application Setup: Install dependencies and set up the Java application.
- Initialization: Access /blockchain endpoint to initialize the blockchain with a name.
- Genesis Block Creation: Access /blockchain/genesis to create the genesis block.
- Adding Candidates: Use the /add-candidate endpoint to add candidates for the election.
- Voting: Access the /vote endpoint to cast votes for the candidates.
- Viewing Results: Check the /candidates endpoint to view the current state of candidates and their votes. 

## Note
This project is intended for educational purposes and serves as a simplified example of blockchain-based voting systems.
It may not cover all edge cases and security considerations required for production-grade applications.