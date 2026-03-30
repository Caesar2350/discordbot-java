# Nexus AI Discord Bot 🤖

Multi-AI Discord bot built with Java + JDA.

## Features
- Multi-AI system (Gemini + Grok)
- Switch AI using `!ai`
- Ask questions using `!ask`
- Clean scalable architecture

## Commands
- !ping → test bot
- !help → show commands
- !ask → ask AI
- !ai → switch AI model

## Tech Stack
- Java
- JDA
- Gemini API
- Grok API

## Setup
1. Add environment variables:
   - BOT_TOKEN
   - API_KEY
   - GROK_API_KEY
2. Run:
   ```bash
   mvn clean package
   java -jar target/app.jar
