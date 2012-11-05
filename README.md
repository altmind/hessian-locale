# Why #
[Recent changes in Java 7 Locale api](http://weblogs.java.net/blog/joconner/archive/2011/09/13/changes-java-7-locale) have changed internal representation of Locale. The latest version of Hessian Serializer is not compatible with those changes. When doing readResolve Locale class expects baseLocale property to be present, hovewer it is not.

This behavior can be overriden with simple custom Serializer. The work based on [blog post of A.J](http://ayax79.wordpress.com/2009/02/11/big-integers-and-hessian/). Thank you pal.

The serializer is very simplistic and basically persists only country, language and variant of locale.

# Examples #

The following code is in groovy:

## Serialize ##

<pre>
ByteArrayOutputStream baos = new ByteArrayOutputStream();
Hessian2Output out = new Hessian2Output(baos);
def sf = SerializerFactory.createDefault()
sf.addFactory(new LocaleSerializerFactory())
out.serializerFactory=sf
ut = envelope.wrap(out);
out.startMessage();
out.writeObject(entry);
out.completeMessage();
out.close();</pre> 
## Deserialize ##
<pre>
ByteArrayInputStream bais = new ByteArrayInputStream(byteval);
Hessian2Input inp = new Hessian2Input(bais);
def sf = SerializerFactory.createDefault()
sf.addFactory(new LocaleSerializerFactory())
inp.serializerFactory=sf
inp.startMessage();
def entry = inp.readObject();
inp.completeMessage();
</pre>

# License #
This work is released under pubic domain license.