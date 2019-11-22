FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/*
COPY ./nginx.conf /etc/nginx/nginx.conf
COPY ./dist /usr/share/nginx/html
EXPOSE 4200
CMD ["nginx", "-g", "daemon off;"]