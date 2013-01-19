package org.siscemu.core;

public class CPU {
	private boolean reset;
	private int c;
	private int pc;
	private int s;
	private int ma;
	private int md;
	
	private Memory memory;
	
	public CPU() {
		reset();
	}
	
	public Memory getMemory() {
		return memory;
	}
	
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	
	public void reset() {
		reset = true;
	}
	
	private int read() {
		return memory.read(ma);
	}
	
	private int write() {
		memory.write(ma, md);
		return md;
	}
	
	public void tick() {
		int nc, npc, ns, nma, nmd;
		
		if(reset) {
			c = 0;
			reset = false;
		}
		
		switch(c) {
		case 0:
			nc = 1;
			npc = 1;
			ns = md;
			nma = 0;
			nmd = s;
			break;
		case 1:
			nc = 2;
			npc = pc+1;
			ns = md - s;
			nma = pc;
			nmd = read();
			break;
		case 2:
			nc = 3;
			npc = pc;
			ns = md;
			nma = md;
			nmd = read();
			break;
		case 3:
			nc = 4;
			npc = pc;
			ns = md - s;
			nma = md;
			nmd = read();
			break;
		case 4:
			nc = 5;
			npc = ma;
			ns = md;
			nma = pc;
			nmd = read();
			break;
		case 5:
			nc = 6;
			npc = ma + 1;
			ns = md - s;
			nma = pc;
			nmd = read();
			break;
		case 6:
			nc = 7;
			npc = (s < 0 ? md : pc);
			ns = md;
			nma = ma;
			nmd = s;
		case 7:
			nc = 1;
			npc = pc + 1;
			ns = md - s;
			nma = pc;
			nmd = write();
			break;
		default:
			throw new IllegalStateException("Unknown clock value:" + c);
		}
		c = nc;
		pc = npc;
		s = ns;
		ma = nma;
		md = nmd;
	}
}
