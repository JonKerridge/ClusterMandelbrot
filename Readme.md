ClusterMandelbrot is a demonstration of the use of the 
cluster_cli library (https://github.com/JonKerridge/clusterFramework)
that is used to create parallel applications running on a cluster of workstation from
a definition file that use a picocli (https://picocli.info/) style DSL specification.

The following is the DSL specification of the Mandelbrot application,
see https://en.wikipedia.org/wiki/Mandelbrot_set .  It describes a three cluster
system with 1 workstation node in each cluster.  The version specifies the software version used
to parse the specification.  A check is made to ensure that host and other node software versions match.

<pre>
version 2.0.4
emit -n 1 -w 1 -p int,int!512,2048
work -n 1 -w 4 -m calculateColour
collect -n 1 -w 1
</pre>

<pre>
where:
-n(odes) specifies the number of nodes in a cluster
-w(orkers) specifies the number of internal worker processes in each node, 
          to exploit multicore architectures
-p specifies a parameter string in the form:
    type specifications!parameter values
    there must be as many parameter values entries as the value of (n x w_
    each separated by !
-m(ethod) specifies the name of the method to be called in the cluster
</pre>

The code files MandelbrotData and MandelbrotCollect contain the detail
of the required operations on the data.
MandelbrotData contains the definition of the method calculateColour() as well as 
a constructor and a create() method
MandelbrotCollect contains the definition of a collate() and a finalise() 
method used in the final collect cluster.

The DSL specification is parsed using the RunParser class.
The correct operation of the application can be checked using a loopback network
where the host is allocated to IP 127.0.0.1 and the cluster nodes to other addresses
in the set 127.0.0.?. The scripts RunLocalHost? can be used to invoke various specifications.
The Node? scripts can be used to run the required loopback nodes.

The scripts NetHost and NetNode are examples of the jar artifacts that can be created to run the
application on a real networked cluster.  
The NetHost should be placed in a folder on the host workstation together with the class files
mandelbrot.MandelbrotCollect and mandelbrot.Mandelbrotdata.  The ?.clicstruct files should 
also be placed in the same folder.  The timing output file created by the host will be 
written to the same folder.
The host node is invoked, for example by:

<pre>
java -jar NetHost.jar ./mandelbrot1n4w512
</pre>

The host will confirm its IP address which is required when invoking the other nodes.

The other nodes in the network will only require the NetNode.jar file and are invoked by:

<pre>
java -jar NetNode.jar host-IP-address
</pre>

The NetNode.jar can be used to invoke any application not just the Mandelbrot example, 
provided the software version matches.

