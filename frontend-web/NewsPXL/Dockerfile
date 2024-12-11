# Step 1: Build the Angular application
FROM node:18-alpine AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .


#build the angular app
RUN npm run build --configuration=production
# Step 2: Use Nginx to serve the app
FROM nginx
# Copy the built files from the previous stage to Nginx's html folder
COPY --from=build /app/dist/news-pxl /usr/share/nginx/html

COPY nginx.conf /etc/nginx/nginx.conf
# Start Nginx
CMD ["nginx", "-g", "daemon off;"]

#Build the Docker image:
#docker build -t angular-nginx-app .

#Run the Docker container
#docker run -p 80:80 angular-nginx-app