@Grab('net.spy:spymemcached:2.10.2')

def client = new net.spy.memcached.MemcachedClient(new InetSocketAddress('127.0.0.1', 11211))

def key = "key-${new Random().nextInt(9999)}"
def value = "value=${new Random().nextInt(9999)}"

client.set(key, 3600, value)

println "key ${key} => ${client.get(key)}"

client.shutdown();
