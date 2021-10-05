# experimentation
Explore various ideas through experimentation

master has the template readme files
docs has the replaced readme files
ci updates the docs branch from master

must exclude the docs branch from ci

Alternatively, could use wiki.  can push to the project wiki repo.
but what about the replacement?
still would be good to have two branches for that.
even better would be able to replacement occur in a reentrant manner.
so template tags would need to be invisible tokens in the pages.
Can use html comments:
<!--- just --->
here's another [][comment]
[comment]: # kjhklhljk
could use a custom language, eg:

```bench::benchMark1.txt
dssdfssdf
```

but then cant have a table.  is that a problem?

```
dssdfssdf,asdasd,sdfdf,dfgdfg
```

This approach could also be used for the github pages.
Which is better, wiki or github pages?
wiki won't trigger ci

https://github.com/solubris/experimentation.wiki.git


