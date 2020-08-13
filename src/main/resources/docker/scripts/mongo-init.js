db.auth('admin-user', 'admin-pwd')

db = db.getSiblingDB('mongo-spring')

db.createUser({
    user:"root",
    pwd:"root",
    roles:[{role:"readWrite", db:"mongo-spring"}]
});
