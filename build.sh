#!/bin/bash

REGION="us-east-1"
ACCOUNT_ID="454726657221"
REPO_NAME="backend-image-repo"
IMAGE_NAME="task-manager-backend"

# Authenticate Docker with ECR
aws ecr get-login-password --region $REGION | docker login --username AWS --password-stdin $ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com

# Build the Docker image
docker build -t $IMAGE_NAME .

# Tag the Docker image
docker tag $IMAGE_NAME:latest $ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/$REPO_NAME:latest

# Push the Docker image
docker push $ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/$REPO_NAME:latest
