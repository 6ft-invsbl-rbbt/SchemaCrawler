package schemacrawler.test.commandline.parser;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static schemacrawler.test.utility.CommandlineTestUtility.parseCommand;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import schemacrawler.schemacrawler.InfoLevel;
import schemacrawler.tools.commandline.command.LoadCommand;
import schemacrawler.tools.commandline.state.SchemaCrawlerShellState;

public class InfoLevelOptionsTest
{

  @Test
  public void noArgs()
  {
    final String[] args = new String[0];

    final SchemaCrawlerShellState state = new SchemaCrawlerShellState();
    final LoadCommand optionsParser = new LoadCommand(state);

    assertThrows(CommandLine.ParameterException.class,
                 () -> parseCommand(optionsParser, args));
  }

  @Test
  public void noValidArgs()
  {
    final String[] args = { "--some-option" };

    final SchemaCrawlerShellState state = new SchemaCrawlerShellState();
    final LoadCommand optionsParser = new LoadCommand(state);

    assertThrows(CommandLine.ParameterException.class,
                 () -> parseCommand(optionsParser, args));
  }

  @Test
  public void infoLevelBadValue()
  {
    final String[] args = { "--info-level", "someinfolvl" };

    final SchemaCrawlerShellState state = new SchemaCrawlerShellState();
    final LoadCommand optionsParser = new LoadCommand(state);

    assertThrows(CommandLine.ParameterException.class,
                 () -> parseCommand(optionsParser, args));
  }

  @Test
  public void infoLevelNoValue()
  {
    final String[] args = { "--info-level" };

    final SchemaCrawlerShellState state = new SchemaCrawlerShellState();
    final LoadCommand optionsParser = new LoadCommand(state);

    assertThrows(CommandLine.ParameterException.class,
                 () -> parseCommand(optionsParser, args));
  }

  @Test
  public void infoLevelWithValue()
  {
    final String[] args = {
      "--info-level", "detailed", "additional", "-extra" };

    final SchemaCrawlerShellState state = new SchemaCrawlerShellState();
    final LoadCommand optionsParser = new LoadCommand(state);
    parseCommand(optionsParser, args);

    final InfoLevel schemaInfoLevel = optionsParser.getInfoLevel();

    assertThat(schemaInfoLevel, is(InfoLevel.detailed));

  }

}
