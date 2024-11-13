# Stage 1: Build the frontend application
FROM node:14.18-alpine AS build

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json (or yarn.lock) to install dependencies
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy all source files
COPY . .

# Build the application
RUN npm run build --configuration=production
# Stage 2: Serve the application with Nginx
FROM nginx

# Copy build files from the previous stage
COPY --from=build /app/build /usr/share/nginx/html

COPY nginx.conf /etc/nginx/nginx.conf

# Runs Nginx in the foreground to serve the application.
CMD ["nginx", "-g", "daemon off"]