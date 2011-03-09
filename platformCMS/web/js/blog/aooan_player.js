
	var sleeptime = 250;
	var n = 0;
	var murl = new Array;
	var mname = new Array;
	var playing = 1;
	var userstop = false
	function addlist(murls,mnames)
	{
	    n++;
	    murl[n] = murls;
	    mname[n] = mnames;
	}

	function splay(num)
	{
	    document.player.URL=murl[num]; 
	    document.player.controls.play();
	    playing = num;
	}

	function getplay()
	{
	    switch (document.player.playState)
	    {
	        case 1:
	            if (userstop == true)
	            {
	            	
	                break;
	            }
	            else
	            {
	                playnext();
	                break;
	            }
	        case 2:
	            break;
	        case 3:
	            break;
	        case 4:
	            break;
	        case 5:
	            break;
	        case 6:
	            break;
	        case 7:
	            break;
	        case 8:
	            break;
	        case 9:
	            break;
	        case 10:
	            break;
	        default:
	            break;
	    }
	    setTimeout("getplay()",sleeptime)
	}

	function playnext()
	{
	    if(playing==n)
	    {
	        splay(1);
	    }
	    else
	    {
	        playing++;
	        splay(playing)
	    }
	}

	function musicload()
	{
	    splay(1);
	    getplay();
	}