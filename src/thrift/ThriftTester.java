package thrift;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import lamedb.KeyValue;
import lamedb.LameDB;
import lamedb.LameDB.Client;
import lamedb.lamedbConstants;

public class ThriftTester {

	private int testLen = 1;
	private String[] args;

	public final static void main(String[] args) throws Exception {
		ThriftTester tester = new ThriftTester(args);
		tester.doTests();
	}



	
	public ThriftTester(String[] args2)
	{
		this.args = args2;
	}
	
	public void doTests(){
		try
		{
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					failGet(0, client);
					successPost(myId,client);
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return successPost(myId,client);
						}
					});
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Success PUT: passed!");
				else
					System.err.println("Success PUT: failed!");        		
			}
	
	
	
	
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return failPost(myId, client);
						}
					});        		
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Fail PUT: passed!");
				else
					System.err.println("Fail PUT: failed!");        		
	
			}
	
	
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return successGet(myId, client);
						}
					});        		
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Success GET: passed!");
				else
					System.err.println("Success GET: failed!");
			}
	
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return failGet(myId, client);
						}
					});        		
				}
	
	
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Fail GET: passed!");
				else
					System.err.println("Fail GET: failed!");
			}
	
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return successUpdate(myId, client);
						}
					});        		
				}
	
	
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;			
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Success UPDATE: passed!");
				else
					System.err.println("Success UPDATE: failed!");
			}
			
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return failUpdate(myId, client);
						}
					});        		
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Fail UPDATE: passed!");
				else
					System.err.println("Fail UPDATE: failed!");
			}
	
	
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return successRemove(myId, client);
						}
					});        		
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Success REMOVE: passed!");
				else
					System.err.println("Success REMOVE: failed!");
			}
	
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return failRemove(myId, client);
						}
					});        		
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Fail REMOVE: passed!");
				else
					System.err.println("Fail REMOVE: failed!");
			}
				
				
	
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return successLongPostGet(myId, client);
						}
					});        		
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Success long PUT&GET: passed!");
				else
					System.err.println("Success long PUT&GET: failed!");
			}
				
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return successUpdateWithVersion(myId, client);
						}
					});        		
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Success long UPDATE WITH VERSION: passed!");
				else
					System.err.println("Success long UPDATE WITH VERSION: failed!");
			}
	
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return getRange(7*testLen, 7*testLen, client);
						}
					});        		
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Success long REMOVE WITH VERSION: passed!");
				else
					System.err.println("Success long REMOVE WITH VERSION: failed!");
			}
				
			{			
				ExecutorService executor = Executors.newFixedThreadPool(100);
				Set<Callable<Boolean>> callables = new HashSet<Callable<Boolean>>(); 
				for(long id = 0; id < 1*testLen; id++)
				{
					TTransport transport = new TSocket(args[0], Integer.parseInt(args[1]));
					transport.open();
	
					TProtocol protocol = new  TBinaryProtocol(transport);
					final Client client = new LameDB.Client(protocol);
	
					final long myId = id;
					
					callables.add(new Callable<Boolean>(){
						public Boolean call() throws Exception {
							return successRemoveWithVersion(myId, client);
						}
					});        		
				}
	
				List<Future<Boolean>> futures = executor.invokeAll(callables);
				boolean result = true;
				for(Future<Boolean> f : futures) {
					result = result && f.get();
	
					if(!result)
						break;
				}
	
				if(result)
					System.out.println("Success long REMOVE WITH VERSION: passed!");
				else
					System.err.println("Success long REMOVE WITH VERSION: failed!");
	
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
						
	}

	private boolean successPost(long id, Client client) throws Exception {
		int resp = client.put(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()));
		if(resp == 0)
			return true;
		else
			return false;
	}

	/*
	 * A especificacao atual nao permite diferenciar sucesso de insercao
	 * com falha em que o dado estava na versao 0.
	 */
	private boolean failPost(long id, Client client) throws Exception 
	{
		int resp = client.put(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()));
		if(resp == 0)
			return true;
		else
			return false;
	}


	private boolean successGet(long id, Client client) throws Exception 
	{
		KeyValue resp = client.get(id);
		if(resp.key == 0) //&& resp.value == id && resp.version == 0
			return true;
		else
			return false;
	}

	
	private boolean failGet(long id, Client client) throws Exception
	{
		KeyValue resp = client.get(id);
		if(resp.version == -1)
			return true;
		else
			return false;
	}

	private boolean getRange(long idl, long idr, Client client) throws Exception 
	{
		List<KeyValue> resp = client.getRange(idl,idr);
		if(resp.size() == testLen) //&& resp.value == id && resp.version == 0
			return true;
		else
			return false;
	}

	private boolean successUpdate(long id, Client client) throws Exception {
		int resp = client.update(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()));
		if(resp == 1)
			return true;
		else
			return false;
	}

	private boolean successUpdateWithVersion(long id, Client client) throws Exception {
		int resp = client.put(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()));
		if(resp != 0)
		{	
			return false;
		}
		else
		{		
			resp = client.updateWithVersion(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()),0);
			resp = client.updateWithVersion(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()),1);
			resp = client.updateWithVersion(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()),2);
			resp = client.updateWithVersion(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()),3);
			if(resp == 4)
				return true;
			else
				return false;
		}
	}

	private boolean successRemoveWithVersion(long id, Client client) throws Exception {
		int resp = client.put(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()));
		if(resp == 0)
		{	
			resp = client.updateWithVersion(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()),0);
			resp = client.updateWithVersion(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()),1);
			resp = client.updateWithVersion(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()),2);
			resp = client.updateWithVersion(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()),3);
			if(resp == 4)
			{
				KeyValue resp1 = client.removeWithVersion(id,0);
				if(resp1.version == 4 )//&& resp.value == id && resp.version == 0
				{
					return true;
				}
			}
		}
		return false;
	}

	private boolean failUpdate(long id, Client client) throws Exception 
	{
		int resp = client.update(id, ByteBuffer.wrap(Long.toHexString(id).getBytes()));
		if(resp == -1)
			return true;
		else
			return false;
	}


	private boolean successRemove(long id, Client client) throws Exception
	{
		KeyValue resp = client.remove(id);
		if(resp.version == 1) // && data
			return true;
		else
			return false;
	}

	private boolean failRemove(long id, Client client) throws Exception 
	{
		KeyValue resp = client.remove(id);
		if(resp.version == -1)
			return true;
		else
			return false;
	}




	private boolean successLongPostGet(long id, Client client) throws Exception 
	{
		byte [] data = new byte[lamedbConstants.MAX_VALUE_LEN];
		int resp = client.put(id, ByteBuffer.wrap(data));
		if(resp == 0)
		{
			KeyValue data2 = client.get(id);
			if(data2.version == 0)
				if(data2.value.equals(ByteBuffer.wrap(data)))
					return true;
		}
		return false;
	}
}
